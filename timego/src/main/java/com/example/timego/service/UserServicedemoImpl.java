package com.example.timego.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.timego.entity.UserDto;
import com.example.timego.entity.UserEntity;
import com.example.timego.repository.UserRepository;


@Service
public class UserServicedemoImpl implements UserServicedemo {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserEntity save(UserDto userDto) {
		
		UserEntity user = new UserEntity(userDto.getUsername(), userDto.getEmail(), 
				          passwordEncoder.encode(userDto.getPassword()), userDto.getRole());
		return userRepository.save(user);
	}

	   @Override
	    public List<UserEntity> getAllUsers() {
	        List<UserEntity> users = userRepository.findAll();
	        return users;
	    }

	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public UserEntity getUserById(Long id) {
		return userRepository.findById(id).orElse(null);	
	}

	public void updateUser(UserEntity user) {
		userRepository.save(user);		
	}

	

	
}
