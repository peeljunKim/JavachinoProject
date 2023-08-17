package com.example.demo.accomodation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AccomodationReview;

@Repository
public interface AccomodationReviewRepository extends JpaRepository<AccomodationReview, Integer> {

	 public List<AccomodationReview> findByAccomodation_AccomodationNo(int accomodationNo);
}
