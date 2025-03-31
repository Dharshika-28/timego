package com.example.timego.service;

import com.example.timego.entity.BookingEntity;
import com.example.timego.entity.FeedbackEntity;
import com.example.timego.entity.SponsorEntity;
import com.example.timego.entity.UserEntity;
import com.example.timego.repository.BookingRepository;
import com.example.timego.repository.FeedbackRepository;
import com.example.timego.repository.SponsorRepository;
import com.example.timego.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<BookingEntity> getAllBookings() {
        return bookingRepository.findAll();
    }
    
    @Autowired
    private SponsorRepository sponsorRepository;

    public List<SponsorEntity> getAllSponsors() {
        return sponsorRepository.findAll();
    }

    @Autowired
    private FeedbackRepository feedbackREpository;
    
	public List<FeedbackEntity> getAllFeedback() {
		return feedbackREpository.findAll();
	}	

}
