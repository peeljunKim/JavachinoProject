package com.example.demo.accomodation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.accomodation.repository.BusinessRepository;
import com.example.demo.entity.Business;

@Service
public class BusinessService {
	@Autowired
	private BusinessRepository businessRepository;
	
	public Business findByAccomodationNo(int accomodationNo) {
		return businessRepository.findByAccomodationNo(accomodationNo);
	}
}
