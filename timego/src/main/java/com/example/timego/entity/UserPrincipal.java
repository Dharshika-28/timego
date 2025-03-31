package com.example.timego.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {

	private UserEntity user;

	public UserPrincipal(UserEntity user) {
		this.user=user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(){
		return List.of(() -> user.getRole());
	}
	
	@Override
	public String getUsername() {
		return user.getUsername();
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}
	
	public String getEmail() {
		return user.getEmail();
	}


}
