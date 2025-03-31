package com.example.timego.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.timego.entity.ContactpayEntity;
import com.example.timego.entity.PaymentEntity;
import com.example.timego.entity.SponsorEntity;
import com.example.timego.repository.ContactpayRepository;
import com.example.timego.repository.PaymentRepository;
import com.example.timego.repository.SponsorRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    
    @Autowired
    private ContactpayRepository contactpayRepository;

    public List<PaymentEntity> getAllPayments() {
        return paymentRepository.findAll();
    }

	public List<ContactpayEntity> getAllContactPayments() {
		return contactpayRepository.findAll();
	}

	public void paymentById(Long id) {
		paymentRepository.deleteById(id);
	}

	public void contactpayById(Long id) {
		contactpayRepository.deleteById(id);
	}

}
