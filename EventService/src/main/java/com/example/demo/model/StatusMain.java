package com.example.demo.model;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "status_main")
@Data
public class StatusMain {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer statusId;
	@Column(nullable = false)
    private String statusValue;
	@Column(nullable = false)
    private String description;
	@Column(nullable = false)
    private Timestamp createdDate;
	@Column(nullable = false)
    private Timestamp lastModifiedDate;
	@OneToMany(mappedBy = "statusMain", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users;
	@OneToMany(mappedBy = "statusMain", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events;
}
