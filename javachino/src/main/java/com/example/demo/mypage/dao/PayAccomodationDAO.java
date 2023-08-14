package com.example.demo.mypage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.View_PayAccomodation;


@Repository
public interface PayAccomodationDAO extends JpaRepository<View_PayAccomodation, Integer> {
	
}
