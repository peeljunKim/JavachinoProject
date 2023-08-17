package com.example.demo.community.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AccomodationRV;

import jakarta.transaction.Transactional;

@Repository
public interface MainAccomodationRVRepository extends JpaRepository<AccomodationRV, Integer>{
	
	

	 @Query(value="select * from accomodation_rv where users_no = :usersNo", nativeQuery = true)
	    public List<AccomodationRV> findAllByUsersNo(int usersNo);
	

	 public AccomodationRV findByAccomodationRVNo(int accomodationRVNo);
}
