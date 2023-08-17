package com.example.demo.accomodation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Business;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Integer> {
	
	@Query(value =  "select b.* "
			+ "from business b "
			+ "where b.business_no = (select a.business_no from accomodation a where a.accomodation_no = ?1)", nativeQuery = true)
	public Business findManagerAndPhoneByAccomodationNoTest(int accomodationNo);

}
