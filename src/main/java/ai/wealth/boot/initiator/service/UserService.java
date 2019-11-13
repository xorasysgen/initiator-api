package ai.wealth.boot.initiator.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ai.wealth.boot.initiator.configuration.security.model.Authorities;
import ai.wealth.boot.initiator.configuration.security.model.Users;
import ai.wealth.boot.initiator.exception.CommonException;
import ai.wealth.boot.initiator.exception.UserAlreadyExistsException;
import ai.wealth.boot.initiator.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public Users createUserService(Users model) {
		// always create new object and set value from dto
		Optional<Users> existedUser =userRepository.getByUsername(model.getUsername());
		if(existedUser.isPresent()) {
			throw new UserAlreadyExistsException(model.getUsername() + " already exist!");
		}
		System.out.println("model" + model);
		Users user = new Users();
		user.setEnabled(model.isEnabled());
		user.setFullname(model.getFullname());
		user.setPassword(bCryptPasswordEncoder.encode(model.getPassword()));
		user.setUsername(model.getUsername());
		//model.getAuthorities().forEach(authorities-> user.getAuthorities().add(authorities));
		
		for (Authorities authorities : model.getAuthorities()) {
			
			user.getAuthorities().add(authorities);
			authorities.setUsers(user);
			
		}
		 
		Users userObject=userRepository.save(user);
		if(userObject!=null)
		return userObject;
		else
			throw new CommonException(user.getUsername() + " could not be created");
			
	}

	public boolean deleteUserService(Integer id) {
		try {
		userRepository.deleteById(id);
		}catch(Exception ex) {
			throw new CommonException(id + "could not deleted");
		}
		return true;
	}
	
	
	
	public boolean updateUserService(Users user, String username) {
		Users userFromDB=null;
		try {
		Optional<Users> usersData=userRepository.getByUsername(username);
		if(usersData.isPresent()) {
			userFromDB=usersData.get();
			System.out.println("user full name" + userFromDB.getFullname());
			userFromDB.setFullname(user.getFullname());
			userRepository.save(userFromDB);
		}
		else
			throw new CommonException(username + "could not Found");
		
		}catch(Exception ex) {
			throw new CommonException(username + "could not found");
		}
		return true;
	}
	
	public Optional<Users> getUserByUserName(String username) {
		try {
		return userRepository.getByUsername(username);
		}
		catch(Exception e) {
			e.getMessage();
			throw new UsernameNotFoundException("User does ot exsist");
		}
	}
	public List<Users> getUsers() {
		return userRepository.findAll();
	}

	
	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public BCryptPasswordEncoder getbCryptPasswordEncoder() {
		return bCryptPasswordEncoder;
	}

	public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

}
