package ai.wealth.boot.initiator.dto;

import ai.wealth.boot.initiator.configuration.security.model.Authorities;

public class AuthoritiesRoles {


	private String authority;

	public AuthoritiesRoles(Authorities p) {
		this.authority = p.getAuthority();
	}



	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
