package com.example.demo.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "email_details")
@Data
public class EmailDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long emailDetailsId;
	@Column(nullable = false)
	private String fromEmail;
	@Column(nullable = false)
	private String toEmail;
	@Column(nullable = false)
	private String emailTemplateName;
	@Column(nullable = false)
	private String emailLink;
	@Column(nullable = false)
	private String emailSubject;
	@Column(nullable = false)
	private String emailBody;
	@Column(nullable = false)
	private Integer status;
	@Column
	private Timestamp createdDate;
	@Column
	private Timestamp sentDate;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createdBy_id", nullable = false)
    private User createdBy;
	
	
}
