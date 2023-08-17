package com.example.demo.community.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.community.repository.MainActivityRvRepository;
import com.example.demo.entity.ActivityRv;

import jakarta.transaction.Transactional;

@Service
public class MainActivityRvService {

    @Autowired
    private MainActivityRvRepository activityRvRepository;
    
    public List<ActivityRv> findAllByUsersNo(int usersNo){
    	return activityRvRepository.findAllByUsersNo(usersNo);
    }
    
    public ActivityRv findByActivityRvNo(int activityRvNo) {
    	return activityRvRepository.findByActivityRvNo(activityRvNo);
    }
}
