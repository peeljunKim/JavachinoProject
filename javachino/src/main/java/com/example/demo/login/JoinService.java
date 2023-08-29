package com.example.demo.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;

@Service
public class JoinService {
	
	@Autowired
	private MyUsersDAO usersDao;
	
	public Users saveUser(Users user) {
		return usersDao.save(user);
	}
}
