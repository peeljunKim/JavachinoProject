package com.example.demo.community.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Accomodation;
import com.example.demo.community.repository.MainAccomodationRepository;

@Service
public class MainAccomodationService {

	@Autowired
	private MainAccomodationRepository accomodationRepository;
	
	public List<Accomodation> findAllRownum(){
		return accomodationRepository.findAllRownum();
	}
	
	//지역 검색
	public List<Accomodation> findByAccomodationAddr(String keyword){
		return accomodationRepository.findByAccomodationAddr("%"+keyword+"%");
	}
}
