package com.example.demo.accomodation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AccomodationInfo;

@Repository
public interface AccomodationInfoRepository extends JpaRepository<AccomodationInfo, Integer> {
	
	@Query(value = "select * from accomodation_info where accomodation_no = ?1", nativeQuery = true)
	public AccomodationInfo findByAccomodationNo(int accomodationNo);
	
	public List<AccomodationInfo> findByAccomodationInfoMaxPersionGreaterThanEqual(int person);
}
