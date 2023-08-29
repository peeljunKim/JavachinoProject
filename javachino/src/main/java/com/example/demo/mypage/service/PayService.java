package com.example.demo.mypage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.mypage.dao.PayDAO;

import lombok.Setter;

@Service
@Transactional
public class PayService {

	@Autowired
	private PayDAO paydao;
	
	public boolean deleteByPayNo1(int payNo) {
	    try {
	        paydao.deleteByPayNo(payNo);
	        return false ;
	    } catch (Exception e) {
	        return true;
	    }
	}

	
	public void deleteByPayNo(int payNo) {
		paydao.deleteByPayNo(payNo);
	}

}
