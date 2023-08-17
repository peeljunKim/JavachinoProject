package com.example.demo.accomodation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.accomodation.repository.BusinessRepository;
import com.example.demo.entity.Business;

@Service
public class BusinessService {
	@Autowired
	private BusinessRepository businessRepository;
	
	public Business findManagerAndPhoneByAccomodationNo(int accomodationNo) {
		return businessRepository.findManagerAndPhoneByAccomodationNoTest(accomodationNo);
	}
}
