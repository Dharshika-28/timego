package com.example.timego.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "bookings")
public class BookingEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;  // Automatically save logged-in user's username


    private String bookingEmail; 
    private String eventSelect; 
    
    private BigDecimal budget;
    
    private LocalDate fromDate;
    private LocalDate toDate; 
    private LocalTime time;
    
    @Column(columnDefinition = "TEXT")
    private String expectation;

    
    @Column(name = "status")
    private String status = "Pending";  
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookingEmail() {
		return bookingEmail;
	}

	public void setBookingEmail(String bookingEmail) {
		this.bookingEmail = bookingEmail;
	}

	public String getEventSelect() {
		return eventSelect;
	}

	public void setEventSelect(String eventSelect) {
		this.eventSelect = eventSelect;
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getExpectation() {
		return expectation;
	}

	public void setExpectation(String expectation) {
		this.expectation = expectation;
	}
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public BookingEntity() {
		
	}

	public BookingEntity(Long id, String bookingEmail, String eventSelect, BigDecimal budget, LocalDate fromDate,
			LocalDate toDate, LocalTime time, String expectation) {
		super();
		this.id = id;
		this.bookingEmail = bookingEmail;
		this.eventSelect = eventSelect;
		this.budget = budget;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.time = time;
		this.expectation = expectation;
	}
	
	
	 
}
