package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "visit")
public class Visit {
	@Id
	@Column(name = "visit_date")
	private Date visitDate;
	@Column(name = "visit_count")
	private int visitCount;
}

