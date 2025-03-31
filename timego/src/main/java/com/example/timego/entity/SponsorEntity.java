package com.example.timego.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sponsors")
public class SponsorEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;  // Automatically save logged-in user's username

    @Column
    private String companyName;
    @Column
    private String companyEmail; 
    @Column
    private BigDecimal sponsorshipAmount;
    
    @Column(columnDefinition = "TEXT")
    private String message;
    
    @Column(name = "status")
    private String status = "Pending";  
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public BigDecimal getSponsorshipAmount() {
		return sponsorshipAmount;
	}

	public void setSponsorshipAmount(BigDecimal sponsorshipAmount) {
		this.sponsorshipAmount = sponsorshipAmount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public SponsorEntity(Long id, String companyName, String companyEmail, BigDecimal sponsorshipAmount,
			String message) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.companyEmail = companyEmail;
		this.sponsorshipAmount = sponsorshipAmount;
		message = message;
	}

	public SponsorEntity() {

	}

    
    

}

