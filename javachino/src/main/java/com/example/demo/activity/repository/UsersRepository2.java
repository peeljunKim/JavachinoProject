package com.example.demo.activity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Users;

public interface UsersRepository2 extends JpaRepository<Users, Integer> {
	Users findByUsersNo(int usersNo);
}
