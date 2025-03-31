package com.example.timego.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.timego.entity.FeedbackEntity;
import com.example.timego.entity.UserEntity;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
    Optional<UserEntity> findByUsername(String username);
    
}