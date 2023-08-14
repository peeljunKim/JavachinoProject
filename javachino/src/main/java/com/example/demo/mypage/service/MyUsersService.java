package com.example.demo.mypage.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.mypage.dao.MyUsersDAO;

import jakarta.transaction.Transactional;
import lombok.Setter;

@Service
@Setter
public class MyUsersService {
	@Autowired
	private MyUsersDAO dao;
	
	//pk에 해당하는 레코드 하나를 검색
	public Users getOne(String usersId) {
	    Optional<Users> userOptional = dao.findByUsersId(usersId);
	    return userOptional.orElse(null);
	}
	
	@Transactional
	public void updateUser(Users user) {
		dao.save(user);
	}
	
    public Optional<Users> getUserByUsersId(String usersId) {
        return dao.findByUsersId(usersId);
    }


}
