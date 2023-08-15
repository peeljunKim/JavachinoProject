package com.example.demo.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.community.repository.MainUsersRepository;

@Service
public class MainUsersService {
	@Autowired
	private MainUsersRepository usersRepository;
	
	 public Users getUserById(String usersId) {
	        return usersRepository.findByUsersId(usersId);
	    }
}
