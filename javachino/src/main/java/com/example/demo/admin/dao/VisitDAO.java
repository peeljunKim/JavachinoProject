package com.example.demo.admin.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Visit;


public interface VisitDAO extends JpaRepository<Visit, Date> {
	
    List<Visit> findAllByVisitDateBetween(Date startDate, Date endDate);
    
    @Query(value="select count(*) from visit where visit_date = ?1", nativeQuery = true)
	public int countRecord(Date visit_date);
}