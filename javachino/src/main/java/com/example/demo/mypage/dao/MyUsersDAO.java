package com.example.demo.mypage.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Users;

@Repository
public interface MyUsersDAO extends JpaRepository<Users, String> {
	
	Optional<Users> findByUsersId(String usersId);
		
	//로그인
	Users findByUsersIdAndUsersPhone(String usersId, String usersPhone);

}
