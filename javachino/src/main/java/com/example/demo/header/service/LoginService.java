package com.example.demo.header.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.mypage.dao.MyUsersDAO;

@Service
public class LoginService {
	
	@Autowired
	private MyUsersDAO usersDao;

	public Users loginUser(String usersId, String usersPhone) {
		return usersDao.findByUsersIdAndUsersPhone(usersId, usersPhone);
	}
	
}
