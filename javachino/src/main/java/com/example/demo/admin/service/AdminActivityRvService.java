package com.example.demo.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.admin.dao.AdminActivityRvDAO;

import lombok.Setter;

@Service
@Setter
public class AdminActivityRvService {
    @Autowired
    private AdminActivityRvDAO dao;

    public int getActivityRvTotalRecord() {
        return (int) dao.count();
    }
}
