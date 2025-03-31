package com.example.timego.service;

import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.example.timego.entity.FeedbackEntity;
import com.example.timego.entity.UserDto;
import com.example.timego.entity.UserEntity;


@Service
public interface UserServicedemo {
	
	UserEntity save(UserDto userDto);

	List<UserEntity> getAllUsers();

	void deleteById(Long id);

	UserEntity getUserById(Long id);

	void updateUser(UserEntity user);	
}
