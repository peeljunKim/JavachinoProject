package com.example.demo.community.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.community.repository.MainAccomodationRepository;
import com.example.demo.community.repository.MainView_AccomodationListRepository;
import com.example.demo.entity.Accomodation;
import com.example.demo.entity.View_AccomodationList;

@Service
public class MainAccomodationService {

	@Autowired
	private MainAccomodationRepository accomodationRepository;
	
	@Autowired
	private MainView_AccomodationListRepository accomodationListRepository;
	
	public List<Accomodation> findAllRownum(){
		return accomodationRepository.findAllRownum();
	}
	
	//지역 검색
	public List<Accomodation> findByAccomodationAddr(String keyword){
		return accomodationRepository.findByAccomodationAddr("%"+keyword+"%");
	}
	
	public List<View_AccomodationList> findByAccomodationNo(){
		return accomodationListRepository.findAccomodationByNo();
	}
	
	public View_AccomodationList findByAccomodationNo(int accomodationNo){
		return accomodationListRepository.findAccomodationByNo(accomodationNo);
	}
}
