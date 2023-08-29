package com.example.demo.mypage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.ActivityReview;

@Repository
public interface ActivityReviewDAO extends JpaRepository<ActivityReview, Integer> {

	@Modifying
	@Query(value="insert into activity_review (activity_no, activity_review_no, activity_review_date, activity_review_star, users_no, activity_review_content) \r\n"
			+ "values(:activityNo, 100, CURRENT_DATE, :activityReviewStar, :usersNo, :activityReviewContent)",nativeQuery = true)
	@Transactional
	public void insert(
	
		@Param("activityNo") int activityNo,
		@Param("activityReviewStar") double activityReviewStar,
		@Param("usersNo") int usersNo,
		@Param("activityReviewContent") String activityReviewContent
		
	);
	
	
}
