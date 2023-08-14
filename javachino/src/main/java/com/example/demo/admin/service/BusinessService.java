package com.example.demo.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.admin.dao.BusinessDAO;
import com.example.demo.entity.Business;

import lombok.Setter;

@Service
@Setter
public class BusinessService {
	@Autowired
	private BusinessDAO dao;
	
	//전체 레코드 수를 반환하는 메소드 정의 (count가 long을 수식받으니까 int로 변환)
	public int getTotalRecord() {
		return (int)dao.count();
	}
	
	public List<Business> findAll(int start, int end){
		//return dao.findAll();
		return dao.selectAll(start, end);
	}
	
}
