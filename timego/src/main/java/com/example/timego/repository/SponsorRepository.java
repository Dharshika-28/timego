package com.example.timego.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.timego.entity.SponsorEntity;
import com.example.timego.entity.UserEntity;

@Repository
public interface SponsorRepository extends JpaRepository<SponsorEntity,Long>{

	 List<SponsorEntity> findByUsername(String username); 	

}
