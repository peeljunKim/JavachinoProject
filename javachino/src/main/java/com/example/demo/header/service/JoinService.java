package com.example.demo.header.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.mypage.dao.MyUsersDAO;

@Service
public class JoinService {
	
	@Autowired
	private MyUsersDAO usersDao;
	
	public Users saveUser(Users user) {
		return usersDao.save(user);
	}
}
