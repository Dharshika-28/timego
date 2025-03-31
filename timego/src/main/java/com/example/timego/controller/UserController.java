package com.example.timego.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.example.timego.entity.BookingEntity;
import com.example.timego.entity.ContactpayEntity;
import com.example.timego.entity.PaymentEntity;
import com.example.timego.entity.SponsorEntity;
import com.example.timego.repository.BookingRepository;
import com.example.timego.repository.ContactpayRepository;
import com.example.timego.repository.PaymentRepository;
import com.example.timego.repository.SponsorRepository;
import com.example.timego.service.CustomUserDetailsService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	SponsorRepository sponsorRepository;
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	ContactpayRepository contactpayRepository;
	
	@GetMapping("/payment")
	public String payment(Model model) {
		
		model.addAttribute("paymentById", new PaymentEntity());
        model.addAttribute("paymentlist", paymentRepository.findAll());
		return "payment";
	}
	
	@PostMapping("/paymentverify")
	public String payment(@RequestParam("paymentMode") String paymentMode,
	                      @RequestParam("amount") BigDecimal amount,
	                      @RequestParam("fullName") String fullName,
	                      @RequestParam(name = "phoneNumber", required = false) Long phoneNumber,
	                      @RequestParam(name = "cvv", required = false) Integer cvv,
	                      @RequestParam("otp") Integer otp,
	                      @RequestParam(name = "id", required = false) Long id) {

	    // Validate input: Ensure only CVV or phoneNumber is provided, based on payment mode
	    if (paymentMode.equals("creditCard")) {
	        if (cvv == null || cvv.toString().length() != 3) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid CVV. Must be 3 digits.");
	        }
	    } else if (paymentMode.equals("googlePay") || paymentMode.equals("paypal")) {
	        if (phoneNumber == null || phoneNumber.toString().length() < 10) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid phone number. Must be at least 10 digits.");
	        }
	    } else {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid payment mode.");
	    }
	        PaymentEntity paymetnEntity = new PaymentEntity();
	        paymetnEntity.setPaymentMode(paymentMode);
	        paymetnEntity.setAmount(amount);
	        paymetnEntity.setFullName(fullName);
	        paymetnEntity.setPhoneNumber(phoneNumber);
	        paymetnEntity.setCvv(cvv);
	        paymetnEntity.setOtp(otp);

	        if (id != null) {
	        	paymetnEntity.setId(id);
	        }

	        paymentRepository.save(paymetnEntity);
	        return "redirect:/public/contact";
	    }


	@GetMapping("/contactpay")
	public String contactPay(Model model) {
	    
		model.addAttribute("contactpayById", new PaymentEntity());
        model.addAttribute("contactpaylist", paymentRepository.findAll());
	    return "contactpay";
	}

	@PostMapping("/contactpayverify")
	public String contactPayment(
	        @RequestParam("paymentMode") String paymentMode,
	        @RequestParam("amount") BigDecimal amount,
	        @RequestParam("fullName") String fullName,
	        @RequestParam(name = "phoneNumber", required = false) Long phoneNumber,
	        @RequestParam(name = "cvv", required = false) Integer cvv,
	        @RequestParam("otp") Integer otp,
	        @RequestParam(name = "id", required = false) Long id,
	        @RequestParam(name = "paymentType", required = false) String paymentType,
	        Model model) {

	    // ✅ DEBUG: Print received values
	    System.out.println("🚀 contactPayment() method called!");
	    System.out.println("DEBUG: Payment Mode = " + paymentMode);
	    System.out.println("DEBUG: Amount = " + amount);
	    System.out.println("DEBUG: Full Name = " + fullName);
	    System.out.println("DEBUG: Phone Number = " + phoneNumber);
	    System.out.println("DEBUG: CVV = " + cvv);
	    System.out.println("DEBUG: OTP = " + otp);
	    System.out.println("DEBUG: ID = " + id);
	    System.out.println("DEBUG: Payment Type = " + paymentType);

	    // ✅ Validate Payment Details
	    if ("creditCard".equalsIgnoreCase(paymentMode)) {
	        if (cvv == null || String.valueOf(cvv).length() != 3) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid CVV. Must be 3 digits.");
	        }
	    } else if ("googlePay".equalsIgnoreCase(paymentMode) || "paypal".equalsIgnoreCase(paymentMode)) {
	        if (phoneNumber == null || String.valueOf(phoneNumber).length() < 10) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid phone number. Must be at least 10 digits.");
	        }
	    } else {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid payment mode.");
	    }

	    // ✅ Create Payment Entity
	    ContactpayEntity contactpayEntity = new ContactpayEntity();
	    contactpayEntity.setPaymentMode(paymentMode);
	    contactpayEntity.setAmount(amount);
	    contactpayEntity.setFullName(fullName);
	    contactpayEntity.setPhoneNumber(phoneNumber);
	    contactpayEntity.setCvv(cvv);
	    contactpayEntity.setOtp(otp);
	    contactpayEntity.setPaymentType(paymentType); // Ensure this exists in your entity

	    if (id != null) {
	        contactpayEntity.setId(id);
	    }

	    contactpayRepository.save(contactpayEntity);

	    return "redirect:/public/contact";
	}




	@GetMapping("/prebook")
	public String prebook(Model model) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();

	    if (username.equals("anonymousUser")) {
	        return "redirect:/public/login";
	    }

	    List<SponsorEntity> sponsorList = sponsorRepository.findByUsername(username);
	    List<BookingEntity> bookingList = bookingRepository.findByUsername(username);

	    model.addAttribute("sponsorlist", sponsorList);
	    model.addAttribute("bookinglist", bookingList);

	    return "prebook";
	}


}


