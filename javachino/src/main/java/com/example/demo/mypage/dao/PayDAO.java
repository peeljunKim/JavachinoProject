package com.example.demo.mypage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Pay;

@Repository
public interface PayDAO extends JpaRepository<Pay, Integer> {

	public void deleteByPayNo(int payNo);

}
