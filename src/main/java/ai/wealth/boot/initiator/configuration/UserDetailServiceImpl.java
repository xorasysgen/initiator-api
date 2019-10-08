package ai.wealth.boot.initiator.configuration;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ai.wealth.boot.initiator.configuration.security.model.Users;
import ai.wealth.boot.initiator.repository.UserRepository;

@Service
public class UserDetailServiceImpl  implements UserDetailsService{

	
	@Autowired
	private UserRepository userRepository;
	
	public UserRepository getUserRepository() {
		return userRepository;
	}
	
	

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Users> user=userRepository.getByUsername(username);
		user.orElseThrow(() -> new UsernameNotFoundException("User " + username +  " Not found"));
		return user.map(UserDetailsImpl::new).get();
		//return new UserDetailsImpl(username);
	}

}
