package com.example.demo.accomodation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
	public Users findByUsersNo(int usersNo);
}