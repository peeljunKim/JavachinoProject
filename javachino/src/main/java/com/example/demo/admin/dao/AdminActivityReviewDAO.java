package com.example.demo.admin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ActivityReview;

@Repository
public interface AdminActivityReviewDAO extends JpaRepository<ActivityReview, Integer> {

}