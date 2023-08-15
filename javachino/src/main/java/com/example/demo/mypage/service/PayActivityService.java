package com.example.demo.mypage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ActivityRv;
import com.example.demo.entity.View_PayAccomodation;
import com.example.demo.entity.View_PayActivity;
import com.example.demo.mypage.dao.PayActivityDAO;

import lombok.Setter;


@Service
@Setter
public class PayActivityService {
    @Autowired
    private PayActivityDAO dao;

    public List<View_PayActivity> findAllByUsersNo(int usersNo) {
        return dao.findActivityByUsersNo(usersNo); 
    }
    
    
}

