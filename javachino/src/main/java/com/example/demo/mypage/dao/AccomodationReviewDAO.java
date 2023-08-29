package com.example.demo.mypage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.AccomodationReview;

@Repository
public interface AccomodationReviewDAO extends JpaRepository<AccomodationReview, Integer> {

	@Modifying
	@Query(value="insert into accomodation_review (accomodation_no, accomodation_review_no, accomodation_review_date, accomodation_review_star, users_no, accomodation_review_content) \r\n"
			+ "values(:accomodationNo, 37, CURRENT_DATE, :accomodationReviewStar, :usersNo, :accomodationReviewContent)",nativeQuery = true)
	@Transactional
	public void insert(
	
		@Param("accomodationNo") int activityNo,
		@Param("accomodationReviewStar") double activityReviewStar,
		@Param("usersNo") int usersNo,
		@Param("accomodationReviewContent") String activityReviewContent
		
	);
	
	
}
