package com.example.timego.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class PaymentEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paymentMode;
    
    private BigDecimal amount;
    
    private String fullName;
    
    private Long phoneNumber;
    
    private Integer cvv;

    private Integer otp;

    private LocalDateTime paymentDate = LocalDateTime.now(); // Auto-fill payment date

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getCvv() {
		return cvv;
	}

	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}

	public Integer getOtp() {
		return otp;
	}

	public void setOtp(Integer otp) {
		this.otp = otp;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public PaymentEntity() {
	
	}

	public PaymentEntity(Long id, String paymentMode, BigDecimal amount, String fullName, Long phoneNumber,
			Integer cvv, Integer otp, LocalDateTime paymentDate) {
		super();
		this.id = id;
		this.paymentMode = paymentMode;
		this.amount = amount;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.cvv = cvv;
		this.otp = otp;
		this.paymentDate = paymentDate;
	}

	
    
}
