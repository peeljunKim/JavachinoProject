package com.example.demo.mypage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.View_LikeActivity;
import com.example.demo.mypage.dao.View_LikeActivityDAO;

import lombok.Setter;

@Service
@Setter
public class View_LikeActivityService {
	@Autowired
	private View_LikeActivityDAO dao;
	
	public List<View_LikeActivity> findAll(){
		return dao.findAll();
	}
}
