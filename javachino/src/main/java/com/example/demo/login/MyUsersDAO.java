package com.example.demo.login;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Pay;
import com.example.demo.entity.Users;

import jakarta.transaction.Transactional;

@Repository
public interface MyUsersDAO extends JpaRepository<Users, String> {
	
	Optional<Users> findByUsersId(String usersId);

		
	//로그인
	Users findByUsersIdAndUsersPassword(String usersId, String usersPassword);

    @Transactional
    void deleteByUsersId(String usersId);
}
