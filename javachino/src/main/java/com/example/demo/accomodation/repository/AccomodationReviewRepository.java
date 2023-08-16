package com.example.demo.accomodation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AccomodationReiview;

@Repository
public interface AccomodationReviewRepository extends JpaRepository<AccomodationReiview, Integer> {

	 public List<AccomodationReiview> findByAccomodation_AccomodationNo(int accomodationNo);
}
