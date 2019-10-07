package ai.wealth.boot.initiator.configuration;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private String userName;
	
	public UserDetailsImpl(String userName) {
		this.userName = userName;
	}
	
	public UserDetailsImpl() {
		
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.println("username" + userName);
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		return userName;
	}

	@Override
	public String getUsername() {
		return userName;
	}




	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

}
