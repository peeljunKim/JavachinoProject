package com.example.demo.mypage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mypage.dao.AccomodationReviewDAO;

import lombok.Setter;

@Service("accomodationReviewService2")
@Setter
public class AccomodationReviewService2 {

	@Autowired
	private AccomodationReviewDAO dao;

	public void insert(int accomodationNo, double accomodationReviewStar, int usersNo, String accomodationReviewContetnt ) {
		System.out.println("accomodation_review service 전");
		dao.insert( accomodationNo, accomodationReviewStar, usersNo, accomodationReviewContetnt );
		System.out.println("accomodation_review service 후");
	}
}
