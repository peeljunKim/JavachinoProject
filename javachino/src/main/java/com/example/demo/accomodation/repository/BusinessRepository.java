package com.example.demo.accomodation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Business;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Integer> {
	
	@Query(value = "select b.business_manager, b.business_phone from accomodation a, business b "
			+ "where a.business_no = b.business_no and "
			+ "a.accomodation_no = ?1", nativeQuery = true)
	public Business findByAccomodationNo(int accomodationNo);
}
