package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "accomodation")
@SequenceGenerator(name = "ACCOMODATION_SEQ_GEN", 
sequenceName = "ACCOMODATION_SEQ", 
initialValue = 1, 
allocationSize = 1)
public class Accomodation {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOMODATION_SEQ_GEN")
	@Column(name = "accomodation_no")
	private int accomodationNo;
	@Column(name = "accomodation_name")
	private String accomodationName;
	@Column(name = "accomodation_addr")
	private String accomodationAddr;
	@Column(name = "accomodation_price")
	private String accomodationPrice;
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "accomodation_category")
	private AccomodationCategory accomodationCategory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "business_no", foreignKey = @ForeignKey(name = "fk_accom_to_business"))
	private Business business;

	public int getAccomodationNo() {
		return accomodationNo;
	}

	public void setAccomodationNo(int accomodationNo) {
		this.accomodationNo = accomodationNo;
	}

	public String getAccomodationName() {
		return accomodationName;
	}

	public void setAccomodationName(String accomodationName) {
		this.accomodationName = accomodationName;
	}

	public String getAccomodationAddr() {
		return accomodationAddr;
	}

	public void setAccomodationAddr(String accomodationAddr) {
		this.accomodationAddr = accomodationAddr;
	}

	public String getAccomodationPrice() {
		return accomodationPrice;
	}

	public void setAccomodationPrice(String accomodationPrice) {
		this.accomodationPrice = accomodationPrice;
	}

	public AccomodationCategory getAccomodationCategory() {
		return accomodationCategory;
	}

	public void setAccomodationCategory(AccomodationCategory accomodationCategory) {
		this.accomodationCategory = accomodationCategory;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	
}
