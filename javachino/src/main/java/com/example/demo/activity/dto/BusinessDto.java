package com.example.demo.activity.dto;

import com.example.demo.entity.BusinessCategory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessDto {
    private int businessNo;
    private String businessName;
    private String businessAddr;
    private String businessPhone;
    private String businessManager;
    private BusinessCategory businessCategory;

    public BusinessDto(int businessNo, String businessName, String businessAddr, String businessPhone, String businessManager, BusinessCategory businessCategory) {
        this.businessNo = businessNo;
        this.businessName = businessName;
        this.businessAddr = businessAddr;
        this.businessPhone = businessPhone;
        this.businessManager = businessManager;
        this.businessCategory = businessCategory;
    }

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