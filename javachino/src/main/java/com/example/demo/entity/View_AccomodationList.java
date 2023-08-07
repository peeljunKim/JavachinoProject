package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="view_accomodation_list")
public class View_AccomodationList {
	
	@Id
	private int accomodation_no;
    private String accomodation_addr;
    private String accomodation_name;
    private String accomodation_price;
    private String accomodation_file_fname;
    private int review_count;
    private double avg_review_star;
}