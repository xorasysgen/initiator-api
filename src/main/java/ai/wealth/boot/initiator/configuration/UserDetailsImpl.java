package ai.wealth.boot.initiator.configuration;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ai.wealth.boot.initiator.configuration.security.model.Users;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String password;
	@SuppressWarnings("unused")
	private String fullname;
	private boolean enabled;
	private List<GrantedAuthority> authorities;
	
	public UserDetailsImpl(Users user) {
		super();
		this.userName = user.getUsername();
		this.password = user.getPassword();
		this.fullname = user.getFullname();
		this.enabled = user.isEnabled();
		this.authorities = user.getAuthorities().stream().map(p-> new SimpleGrantedAuthority(p.getAuthority())).collect(Collectors.toList());
		//user.getAuthorities().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}


	public UserDetailsImpl() {
		
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}


	@Override
	public String getPassword() {
		return password;
	}


	@Override
	public String getUsername() {
		return userName;
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


	@Override
	public boolean isEnabled() {
		return enabled;
	}
	


}
