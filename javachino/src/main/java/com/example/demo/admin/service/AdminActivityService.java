package com.example.demo.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.admin.dao.AdminActivityDAO;
import com.example.demo.entity.Activity;

import lombok.Setter;

@Service
@Setter
public class AdminActivityService {
    @Autowired
    private AdminActivityDAO dao;

    //추가
    public void insert(Activity a) {
        dao.insert(a);
    }
    
}
