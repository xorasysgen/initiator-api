package ai.wealth.boot.initiator.dto;

import java.util.List;
import java.util.stream.Collectors;

import ai.wealth.boot.initiator.configuration.security.model.Users;

public class User {

	private String username;
	private String password;
	private String fullname;
	private boolean enabled;
	private List<AuthoritiesRoles> authoritiesRoles;

	public User(Users user) {
		this.username=user.getUsername();
		this.password=user.getPassword();
		this.fullname=user.getFullname();
		this.enabled=user.isEnabled();
		this.authoritiesRoles=user.getAuthorities().stream().map(p-> new AuthoritiesRoles(p)).collect(Collectors.toList());
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<AuthoritiesRoles> getAuthorities() {
		return authoritiesRoles;
	}

	public void setAuthorities(List<AuthoritiesRoles> authoritiesRoles) {
		this.authoritiesRoles = authoritiesRoles;
	}

}
