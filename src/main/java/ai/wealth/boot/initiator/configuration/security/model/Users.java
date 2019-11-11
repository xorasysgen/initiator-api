package ai.wealth.boot.initiator.configuration.security.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;

import ai.wealth.boot.initiator.exception.annotation.MaxUserRoleConstraint;



@Entity
@Audited
public class Users {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;
	
	@NotNull
	@Size(min=5, message="Name should have atleast 5 characters")
	private String username;
	
	@NotNull
	@Size(min=10, message="Password should have atleast 10 characters")
	private String password;
	private String fullname;
	private boolean enabled;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "users", fetch = FetchType.EAGER,orphanRemoval = true)
	@NotNull
	@MaxUserRoleConstraint // custom validation annotation
	private List<@Valid Authorities> authorities=new ArrayList<>();

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public List<Authorities> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authorities> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", username=" + username + ", password=" + password + ", fullname="
				+ fullname + ", enabled=" + enabled + ", authorities=" + authorities + "]";
	}

	// mapped by is similar to inverse="true" inverse means relationship owner //fetch=FetchType.LAZY
	//if you are returning JPA Entity
	//@JsonIgnore annotation to simply ignore one of the sides of the relationship, thus breaking the chain. 
	//we will prevent the infinite recursion by ignoring the “User”
	
	
	
	
	
	
}
