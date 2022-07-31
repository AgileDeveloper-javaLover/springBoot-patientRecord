package com.Blackpool.Models;

public class AuthenticationResponse {
	private String Jwt;
	
	public AuthenticationResponse() {
		
	}

	public AuthenticationResponse(String jwt) {
		super();
		Jwt = jwt;
	}

	public String getJwt() {
		return Jwt;
	}

	public void setJwt(String jwt) {
		Jwt = jwt;
	}
	
	
}
