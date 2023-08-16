package com.example.demo.activity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Activity;
import com.example.demo.entity.ActivityReview;

public interface ActivityReviewRepository extends JpaRepository<ActivityReview, Integer> {

	List<ActivityReview> findByActivity_ActivityNo(int activityNo);
	List<ActivityReview> findByActivity(Activity activity);
}
