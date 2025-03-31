package com.example.timego.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.timego.entity.BookingEntity;
import com.example.timego.entity.ContactpayEntity;
import com.example.timego.entity.FeedbackEntity;
import com.example.timego.entity.PaymentEntity;
import com.example.timego.entity.SponsorEntity;
import com.example.timego.entity.UserEntity;
import com.example.timego.service.AdminService;
import com.example.timego.service.CustomUserDetailsService;
import com.example.timego.service.PaymentService;
import com.example.timego.service.UserServicedemo;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserServicedemo userServicedemo;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping("/dashboard")
    public String admin(Model model, Principal principal) {
        List<UserEntity> users = userServicedemo.getAllUsers();
        model.addAttribute("userslist", users);
        model.addAttribute("edituser", new UserEntity());


        if (principal != null) {
            model.addAttribute("user", principal.getName());
            
        }
            List<SponsorEntity> sponsors = adminService.getAllSponsors();
            List<BookingEntity> bookings = adminService.getAllBookings();
            
            model.addAttribute("adminsponsorlist", sponsors);
            model.addAttribute("adminbookinglist", bookings);    
            
            List<PaymentEntity> payments = paymentService.getAllPayments();
            model.addAttribute("paymentlist",payments);
            
            List<ContactpayEntity> contactpay = paymentService.getAllContactPayments();
            model.addAttribute("contactpaylist",contactpay);
            
            List<FeedbackEntity> feedback = adminService.getAllFeedback();
            model.addAttribute("feedbacklist",feedback);
        
        return "admin";
    }
	
    @GetMapping("/edit/{id}")
    public String edituser(Model model, @PathVariable("id") Long id) {
    	UserEntity users = userServicedemo.getUserById(id);
        model.addAttribute("edituser", users);

        return "redirect:/registeration";
    }

    @GetMapping("/delete/{id}")
    public String deleteuser(@PathVariable("id") Long id) {
    	userServicedemo.deleteById(id);
        return "redirect:/admin/dashboard";
    }
    
    @GetMapping("/deletepayment/{id}")
    public String deletepayment(@PathVariable("id") Long id) {
    	paymentService.paymentById(id);
        return "redirect:/admin/dashboard";
    }

    
    
	@GetMapping("/deletecontactpay/{id}")
	public String deleteContactPay(@PathVariable("id") Long id) {
		paymentService.contactpayById(id);
	    return "redirect:/admin/dashboard";
	}

}
