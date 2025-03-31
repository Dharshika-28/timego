package com.example.timego.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.timego.entity.ContactpayEntity;

@Repository
public interface ContactpayRepository extends JpaRepository<ContactpayEntity,Long> {
	

}
