package com.example.timego.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "feedback")
public class FeedbackEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String emailId;
    private String review;
   
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public FeedbackEntity() {
	
	}
	public FeedbackEntity(Long id, String username, String emailId, String review) {
		super();
		this.id = id;
		this.username = username;
		this.emailId = emailId;
		this.review = review;
	}
	public FeedbackEntity(String username, String emailId, String review) {
		super();
		this.username = username;
		this.emailId = emailId;
		this.review = review;
	}
    
    
    
}
