package com.example.demo.mypage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.View_PayAccomodation;
import com.example.demo.mypage.dao.PayAccomodationDAO;

import lombok.Setter;


@Service
@Setter
public class PayAccomodationService {
	@Autowired
	private PayAccomodationDAO dao;
	
	public List<View_PayAccomodation> findAll(){
		return dao.findAll();
	}
}
