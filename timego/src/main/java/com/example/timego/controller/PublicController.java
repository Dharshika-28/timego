package com.example.timego.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.timego.entity.BookingEntity;
import com.example.timego.entity.FeedbackEntity;
import com.example.timego.entity.SponsorEntity;
import com.example.timego.repository.BookingRepository;
import com.example.timego.repository.FeedbackRepository;
import com.example.timego.repository.SponsorRepository;

@Controller
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private FeedbackRepository feedbackRepository;
    
    @Autowired
    private SponsorRepository sponsorRepository;
    
    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/event")
    public String event() {
        return "event";
    }

    @GetMapping("/bookedshows")
    public String bookedshows() {
        return "bookedshows";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("feedbackById", new FeedbackEntity());
        model.addAttribute("feedbacklist", feedbackRepository.findAll());
        return "contact";
    }

    @PostMapping("/feedback")
    public String feedback(@RequestParam("username") String username,
                           @RequestParam("emailId") String emailId,
                           @RequestParam("review") String review,
                           @RequestParam(name = "id", required = false) Long id) {

        FeedbackEntity feedbackEntity = new FeedbackEntity();
        feedbackEntity.setUsername(username);
        feedbackEntity.setEmailId(emailId);
        feedbackEntity.setReview(review);

        if (id != null) {
            feedbackEntity.setId(id);
        }

        feedbackRepository.save(feedbackEntity);
        return "redirect:/public/contact";
    }

    @GetMapping("delete1/{feedbackById}")
	String deletebyid1(@PathVariable("feedbackById") Long id) {
	
		FeedbackEntity feedbackEntity = new FeedbackEntity();
		feedbackEntity.setId(id);
		feedbackRepository.delete(feedbackEntity);
	
		return"redirect:/public/contact";
	}

    @GetMapping("/sponsor")
    public String sponsor(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        if (username.equals("anonymousUser")) {
            return "redirect:/public/login";
        }

        model.addAttribute("sponsorById", new SponsorEntity());
        model.addAttribute("sponsorlist", sponsorRepository.findByUsername(username));
        return "sponsor";
    }

    @PostMapping("/sponsorverify")
    public String sponsor(@RequestParam("companyName") String companyName,
                          @RequestParam("companyEmail") String companyEmail,
                          @RequestParam("sponsorshipAmount") BigDecimal sponsorshipAmount,
                          @RequestParam("message") String message,
                          @RequestParam(name = "id", required = false) Long id) {

        SponsorEntity sponsorEntity = new SponsorEntity();
        sponsorEntity.setCompanyName(companyName);
        sponsorEntity.setCompanyEmail(companyEmail);
        sponsorEntity.setSponsorshipAmount(sponsorshipAmount);
        sponsorEntity.setMessage(message);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        if (username.equals("anonymousUser")) {
            return "redirect:/public/login";
        }

        sponsorEntity.setUsername(username);
        
        if (id != null) {
            sponsorEntity.setId(id);
        }

        sponsorRepository.save(sponsorEntity);
        return "redirect:/public/sponsor";
    }

    @GetMapping("/booking")
    public String booking(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        if (username.equals("anonymousUser")) {
            return "redirect:/public/login";
        }

        model.addAttribute("bookingById", new BookingEntity());
        model.addAttribute("bookinglist", bookingRepository.findByUsername(username));
        return "booking";
    }

    @PostMapping("/bookingverify")
    public String booking(@RequestParam("bookingEmail") String bookingEmail,
                          @RequestParam("eventSelect") String eventSelect,
                          @RequestParam("budget") BigDecimal budget,
                          @RequestParam("fromDate") LocalDate fromDate,
                          @RequestParam("toDate") LocalDate toDate,
                          @RequestParam("time") LocalTime time,
                          @RequestParam("expectation") String expectation,
                          @RequestParam(name = "id", required = false) Long id) {

        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setBookingEmail(bookingEmail);
        bookingEntity.setEventSelect(eventSelect);
        bookingEntity.setBudget(budget);
        bookingEntity.setFromDate(fromDate);
        bookingEntity.setToDate(toDate);
        bookingEntity.setTime(time);
        bookingEntity.setExpectation(expectation);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        if (username.equals("anonymousUser")) {
            return "redirect:/public/login";
        }

        bookingEntity.setUsername(username);
        
        if (id != null) {
            bookingEntity.setId(id);
        }

        bookingRepository.save(bookingEntity);
        return "redirect:/public/booking";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
