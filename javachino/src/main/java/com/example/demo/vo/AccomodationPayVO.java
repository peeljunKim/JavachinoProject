package com.example.demo.vo;

import java.util.Date;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class AccomodationPayVO {
	private int accomodation_no;
	private String accomodation_name;
	private Date accomodation_rv_date;
	private Date accomodation_review_date;
	private String accomodation_file_fname1;
	private int pay_price;
	private int pay_no;
	private double accomodation_review_star;
	private String accomodation_review_content;

	
}

