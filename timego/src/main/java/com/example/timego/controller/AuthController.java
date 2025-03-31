package com.example.timego.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.timego.entity.UserDto;
import com.example.timego.entity.UserEntity;
import com.example.timego.entity.UserPrincipal;
import com.example.timego.service.CustomUserDetailsService;
import com.example.timego.service.UserServicedemo;

@Controller
public class AuthController {

	@Autowired
	private UserServicedemo userServicedemo;
	
	@GetMapping("/login")
	public String showRegisterForm(@ModelAttribute("user") UserDto userDto) {
	    return "login";
	}

    @PostMapping("/registeration")
    public String registerUser(@ModelAttribute("user") UserDto userDto,Model model) {
    	userServicedemo.save(userDto);
	    model.addAttribute("param.error","Registeration successfully done!!");
        return "login";
    }
    
} 