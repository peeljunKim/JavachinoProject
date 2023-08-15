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
	public int getAccomodation_no() {
		return accomodation_no;
	}
	public void setAccomodation_no(int accomodation_no) {
		this.accomodation_no = accomodation_no;
	}
	public String getAccomodation_addr() {
		return accomodation_addr;
	}
	public void setAccomodation_addr(String accomodation_addr) {
		this.accomodation_addr = accomodation_addr;
	}
	public String getAccomodation_name() {
		return accomodation_name;
	}
	public void setAccomodation_name(String accomodation_name) {
		this.accomodation_name = accomodation_name;
	}
	public String getAccomodation_price() {
		return accomodation_price;
	}
	public void setAccomodation_price(String accomodation_price) {
		this.accomodation_price = accomodation_price;
	}
	public String getAccomodation_file_fname() {
		return accomodation_file_fname;
	}
	public void setAccomodation_file_fname(String accomodation_file_fname) {
		this.accomodation_file_fname = accomodation_file_fname;
	}
	public int getReview_count() {
		return review_count;
	}
	public void setReview_count(int review_count) {
		this.review_count = review_count;
	}
	public double getAvg_review_star() {
		return avg_review_star;
	}
	public void setAvg_review_star(double avg_review_star) {
		this.avg_review_star = avg_review_star;
	}
    
}