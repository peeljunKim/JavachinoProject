package com.example.demo.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.admin.dao.AdminAccomodationRvDAO;

import lombok.Setter;

@Service
@Setter
public class AdminAccomodationRvService {
    @Autowired
    private AdminAccomodationRvDAO dao;

    public int getAccomodationTotalRecord() {
        return (int) dao.count();
    }
}
