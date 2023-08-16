package com.example.demo.accomodation.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.accomodation.repository.AccomodationFileRepository;
import com.example.demo.accomodation.repository.AccomodationInfoRepository;
import com.example.demo.accomodation.repository.AccomodationRVRepository;
import com.example.demo.accomodation.repository.View_AccomodationListRepository;
import com.example.demo.entity.Accomodation;
import com.example.demo.entity.AccomodationFile;
import com.example.demo.entity.AccomodationInfo;
import com.example.demo.entity.Activity;
import com.example.demo.entity.View_AccomodationList;

import lombok.Setter;

@Service
@Setter
public class AccomodationService {
	@Autowired
	private View_AccomodationListRepository accomodationListRepository;
	@Autowired
	private AccomodationInfoRepository accomodationInfoRepository;
	@Autowired
	private AccomodationRVRepository accomodationRVRepository;
	@Autowired
	private AccomodationFileRepository accomodationFileRepository;

	public List<View_AccomodationList> findAll() {
		return accomodationListRepository.findAll();
	}
	public List<Integer> findAllAccomodationNumbers(){
		return accomodationListRepository.findAllAccomodationNumbers();
	}
	public List<View_AccomodationList> findByAccomodationAddrContaining(String addr){
		return accomodationListRepository.findByAccomodationAddrContaining(addr);
	}
	public List<View_AccomodationList> findByAccomodationNoIn(Collection<Integer> accomodationNos){
		return accomodationListRepository.findByAccomodationNoIn(accomodationNos);
	}
	public View_AccomodationList findAccomodationByNo(int accomodationNo) {
		return accomodationListRepository.findAccomodationByNo(accomodationNo);
	}
	
	public AccomodationInfo findByAccomodationNo(int accomodationNo){
		return accomodationInfoRepository.findByAccomodationNo(accomodationNo);
	}
	
	public List<AccomodationInfo> findByAccomodationInfoMaxPersionGreaterThanEqual(int person){
		return accomodationInfoRepository.findByAccomodationInfoMaxPersionGreaterThanEqual(person);
	}
	
	public List<Integer> findAccommodationNumbersByDateRange(String startDate, String endDate){ // 체크인 체크 아웃 날짜로 숙소 검색
		return accomodationRVRepository.findAccommodationNumbersByDateRange(startDate, endDate);
	}
	
	public List<String> getDateRangesByAccomodationNo(int accomodationNo){
		return accomodationRVRepository.getDateRangesByAccomodationNo(accomodationNo);
	}
	
	public AccomodationFile findByAccomodation_AccomodationNo(int accomodationNo) {
		return accomodationFileRepository.findByAccomodation_AccomodationNo(accomodationNo);
	}
	
}
