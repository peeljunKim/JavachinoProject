package com.example.demo.mypage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mypage.dao.ActivityReviewDAO;

import lombok.Setter;

@Service("activityReviewService2")
@Setter
public class ActivityReviewService2 {

	@Autowired
	private ActivityReviewDAO dao;

	public void insert(int activityNo, double activityReviewStar, int usersNo, String activityReviewContetnt ) {
		dao.insert( activityNo, activityReviewStar, usersNo, activityReviewContetnt );
	}
}
