package com.example.demo.community.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.community.repository.MainActivityRepository;
import com.example.demo.entity.Activity;

@Service
public class MainActivityService {
	
	@Autowired
	private MainActivityRepository activityRepository;
	
	public List<Activity> findAllRownum(){
		return activityRepository.findAllRownum();
	}
	
	//액티비티 지역 검색
	public List<Activity> findByActivityAddr(String keyword){
		return activityRepository.findByActivityAddr("%"+keyword+"%");
	}
	
	public List<Activity> findBy(){
		return activityRepository.findBy();
	}
}
