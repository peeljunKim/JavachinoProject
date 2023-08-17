package com.example.demo.accomodation.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.accomodation.dto.AccomodationRvDTO;
import com.example.demo.accomodation.repository.AccomodationRVRepository;

import jakarta.transaction.Transactional;

@Service
public class AccomodationRVService {
	@Autowired
	private AccomodationRVRepository accomodationRVRepository;
	
	@Transactional
	public void insertAccomodationRV(AccomodationRvDTO accomodationRvDTO) {
		accomodationRVRepository.insertAccomodationRv(accomodationRvDTO);
	}
}
