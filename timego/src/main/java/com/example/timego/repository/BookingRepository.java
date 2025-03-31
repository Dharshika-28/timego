package com.example.timego.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.timego.entity.BookingEntity;
import com.example.timego.entity.UserEntity;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity,Long>{
	
    List<BookingEntity> findByUsername(String username); 

}
