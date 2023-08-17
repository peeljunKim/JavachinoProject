package com.example.demo.community.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.community.repository.MainAccomodationRVRepository;
import com.example.demo.entity.AccomodationRV;

import jakarta.transaction.Transactional;

@Service
public class MainAccomodationRVService {
	@Autowired
	private MainAccomodationRVRepository accomodationRVDAO;
	
	
	public List<AccomodationRV> findAllByUsersNo(int usersNo){
		return accomodationRVDAO.findAllByUsersNo(usersNo);
	}
	
	public AccomodationRV findByAccomodationRVNo(int accomodationRVNo) {
		return accomodationRVDAO.findByAccomodationRVNo(accomodationRVNo);
	}
}
