package com.example.demo.admin.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AccomodationRV;
import com.example.demo.entity.Activity;

@Repository
public interface AdminAccomodationRvDAO extends JpaRepository<AccomodationRV, Integer> {
	
//	@Query(value = "SELECT a FROM AccomodationRV a WHERE a.activity_rv_no BETWEEN ?1 AND ?2 ORDER BY a.activity_rv_no")
//    public List<AccomodationRV> selectAll(int start, int end);
	
}