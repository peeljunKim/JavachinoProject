package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "business")
@SequenceGenerator(
        name="BUSINESS_SEQ_GEN",
        sequenceName = "BUSINESS_SEQ",
    	initialValue = 1,
    	allocationSize = 1
) 
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Business {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "BUSINESS_SEQ_GEN"
    )
    @Column(name = "business_no")
    private int businessNo;
    @Column(name = "business_name")
    private String businessName;
    @Column(name = "business_addr")
    private String businessAddr;
    @Column(name = "business_phone")
    private String businessPhone;
    @Column(name = "business_manager")
    private String businessManager;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "business_category")
    private BusinessCategory businessCategory;
    
	public int getBusinessNo() {
		return businessNo;
	}
	public void setBusinessNo(int businessNo) {
		this.businessNo = businessNo;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getBusinessAddr() {
		return businessAddr;
	}
	public void setBusinessAddr(String businessAddr) {
		this.businessAddr = businessAddr;
	}
	public String getBusinessPhone() {
		return businessPhone;
	}
	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}
	public String getBusinessManager() {
		return businessManager;
	}
	public void setBusinessManager(String businessManager) {
		this.businessManager = businessManager;
	}
	public BusinessCategory getBusinessCategory() {
		return businessCategory;
	}
	public void setBusinessCategory(BusinessCategory businessCategory) {
		this.businessCategory = businessCategory;
	}
    
}
