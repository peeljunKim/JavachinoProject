package com.example.demo.admin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AccomodationRV;

@Repository
public interface AdminAccomodationRvDAO extends JpaRepository<AccomodationRV, Integer> {
	
	
}