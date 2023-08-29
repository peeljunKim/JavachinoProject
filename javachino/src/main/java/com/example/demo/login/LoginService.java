package com.example.demo.login;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;

@Service
public class LoginService {
	
	@Autowired
	private MyUsersDAO usersDao;

	public Users loginUser(String usersId, String usersPassword) {
		return usersDao.findByUsersIdAndUsersPassword(usersId, usersPassword);
	}
	
}
