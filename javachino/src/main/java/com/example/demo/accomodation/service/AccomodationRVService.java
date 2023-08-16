package com.example.demo.accomodation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.accomodation.repository.AccomodationRVRepository;

import jakarta.transaction.Transactional;

@Service
public class AccomodationRVService {
	@Autowired
	private AccomodationRVRepository accomodationRVDAO;
	
	@Transactional
	public void insertAccomodationRV(int acconodationNo, int userNo, String checkin, String ckeckout, String name, String phone) {
		accomodationRVDAO.insertAccomodationRv(acconodationNo, userNo, checkin, ckeckout, name, phone);
	}
}
