package com.example.demo.admin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AccomodationReiview;

@Repository
public interface AdminAccomodationReviewDAO extends JpaRepository<AccomodationReiview, Integer> {
	
	
}