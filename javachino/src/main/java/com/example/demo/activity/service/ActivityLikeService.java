package com.example.demo.activity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.activity.repository.ActivityLikeRepository;
import com.example.demo.activity.repository.ActivityRepository;
import com.example.demo.activity.repository.UsersRepository2;
import com.example.demo.entity.Activity;
import com.example.demo.entity.ActivityLike;
import com.example.demo.entity.Users;

import jakarta.transaction.Transactional;

@Service
public class ActivityLikeService {

    @Autowired
    private ActivityLikeRepository activityLikeRepository;
    @Autowired
    private UsersRepository2 usersRepository; // Assuming you have a UsersRepository
    @Autowired
    private ActivityRepository activityRepository; // Assuming you have an ActivityRepository
    
    @Transactional
    public void likeActivity(int usersNo, int activityNo) {
        Users user = usersRepository.findByUsersNo(usersNo);
        Activity activity = activityRepository.findByActivityNo(activityNo);

        if (activityLikeRepository.existsByUsersNoAndActivityNo(user, activity)) {
            activityLikeRepository.deleteByUsersNoAndActivityNo(user, activity);
        } else {
            ActivityLike activityLike = new ActivityLike();
            activityLike.setUsersNo(user);
            activityLike.setActivityNo(activity);
            activityLikeRepository.save(activityLike);
        }
    }

    @Transactional
    public void insertActivityLike(int activityNo, int usersNo) {
        activityLikeRepository.insertActivityLike(activityNo, usersNo);
    }
}




