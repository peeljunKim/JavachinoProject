package com.example.demo.admin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AccomodationReview;

@Repository
public interface AdminAccomodationReviewDAO extends JpaRepository<AccomodationReview, Integer> {
	
	
}