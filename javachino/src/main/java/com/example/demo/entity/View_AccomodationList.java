package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="view_accomodation_list")
public class View_AccomodationList {
	
	@Id
	private int accomodationNo;
    private String accomodationAddr;
    private String accomodationName;
    private String accomodationPrice;
    private String accomodationFileFname1;
    private int reviewCount;
    private double avgReviewStar;
	public View_AccomodationList() {
		super();
	}
	public int getAccomodationNo() {
		return accomodationNo;
	}
	public void setAccomodationNo(int accomodationNo) {
		this.accomodationNo = accomodationNo;
	}
	public String getAccomodationAddr() {
		return accomodationAddr;
	}
	public void setAccomodationAddr(String accomodationAddr) {
		this.accomodationAddr = accomodationAddr;
	}
	public String getAccomodationName() {
		return accomodationName;
	}
	public void setAccomodationName(String accomodationName) {
		this.accomodationName = accomodationName;
	}
	public String getAccomodationPrice() {
		return accomodationPrice;
	}
	public void setAccomodationPrice(String accomodationPrice) {
		this.accomodationPrice = accomodationPrice;
	}
	public String getAccomodationFileFname1() {
		return accomodationFileFname1;
	}
	public void setAccomodationFileFname1(String accomodationFileFname1) {
		this.accomodationFileFname1 = accomodationFileFname1;
	}
	public int getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	public double getAvgReviewStar() {
		return avgReviewStar;
	}
	public void setAvgReviewStar(double avgReviewStar) {
		this.avgReviewStar = avgReviewStar;
	}
	
	

	
	
    
}