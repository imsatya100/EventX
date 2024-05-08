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
@Table(name = "events")
@Data
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long eventId;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private String venue;
	@Column(nullable = false)
	private Timestamp startDate;
	@Column(nullable = false)
	private Timestamp endDate;
	@Column(nullable = false)
	private Timestamp createdDate;
	@Column(nullable = false)
	private Timestamp modifiedDate;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_main_id", nullable = false)
    private StatusMain statusMain;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createdBy_id", nullable = false)
    private User createdBy;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modifiedBy_id", nullable = false)
    private User modifiedBy;
	 
	
}
