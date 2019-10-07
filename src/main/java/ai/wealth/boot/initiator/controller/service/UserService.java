package ai.wealth.boot.initiator.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.wealth.boot.initiator.configuration.security.model.Authorities;
import ai.wealth.boot.initiator.configuration.security.model.Users;
import ai.wealth.boot.initiator.controller.repository.UserRepository;
import ai.wealth.boot.initiator.exception.CommonException;
import ai.wealth.boot.initiator.exception.UserAlreadyExistsException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public Users createUserService(Users model) {
		// always create new object and set value from dto
		Users existedUser =userRepository.getByUsername(model.getUsername());
		if(existedUser!=null) {
			throw new UserAlreadyExistsException(model.getUsername() + " already exist!");
		}
		
		Users user = new Users();
		user.setEnabled(model.isEnabled());
		user.setFullname(model.getFullname());
		user.setPassword(model.getPassword());
		user.setUsername(model.getUsername());
		Authorities authorities = new Authorities();
		authorities.setAuthority(model.getAuthorities().get(0).getAuthority());
		authorities.setUsers(user);
		user.getAuthorities().add(authorities);
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
	
	public Users getUserByUserName(String username) {
		return userRepository.getByUsername(username);
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

}
