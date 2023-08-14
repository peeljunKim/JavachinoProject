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
}