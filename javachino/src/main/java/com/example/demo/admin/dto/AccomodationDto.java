package com.example.demo.admin.dto;

import com.example.demo.entity.AccomodationCategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccomodationDto {

	// Accomodation Entity
    private int accomodationNo;
    private String accomodationName;
    private String accomodationAddr;
    private String accomodationPrice;
    private AccomodationCategory accomodationCategory;

    // AccomodationFile Entity
    private int accomodationFileNo;
    private String accomodationFileFname1;
    private String accomodationFileFname2;
    private String accomodationFileFname3;

    // AccomodationInfo Entity
    private int accomodationInfoNo;
    private int accomodationInfoMinPerson;
    private int accomodationInfoMaxPerson;
    private String accommodationInfoSize; 
	private String accommodationInfoExplanation; 
	private String accommodationPriceperPerson; 
    
}
