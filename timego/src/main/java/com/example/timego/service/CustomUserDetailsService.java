package com.example.timego.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.timego.entity.UserEntity;
import com.example.timego.entity.UserPrincipal;
import com.example.timego.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity user = userRepository.findByUsername(username)
			.orElseThrow(()-> new UsernameNotFoundException("User not found"));
		
		
		return new UserPrincipal(user);
	}

    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }
}
