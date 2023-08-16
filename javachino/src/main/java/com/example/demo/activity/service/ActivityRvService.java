package com.example.demo.activity.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.activity.repository.ActivityRepository;
import com.example.demo.activity.repository.ActivityRvRepository;
import com.example.demo.activity.repository.UsersRepository2;

import jakarta.transaction.Transactional;

@Service
public class ActivityRvService {

    @Autowired
    private ActivityRvRepository activityRvRepository;

    @Transactional
    public void reserveActivity(int usersNo, int activityNo, LocalDate activityRvDate, int activityRvPeople, String activityRvPhone) {
        // Call the repository method to insert the reservation
        activityRvRepository.insertActivityRv(activityNo, usersNo, activityRvDate, activityRvPeople, activityRvPhone);
    }
}
