package com.example.demo.accomodation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.accomodation.repository.AccomodationReviewRepository;
import com.example.demo.entity.AccomodationReview;

@Service
public class AccomodationReviewService {
	@Autowired
	private AccomodationReviewRepository accomodationReviewRepository;
	
	 public List<AccomodationReview> findByAccomodation_AccomodationNo(int accomodationNo){
		 return accomodationReviewRepository.findByAccomodation_AccomodationNo(accomodationNo);
	 }

}
