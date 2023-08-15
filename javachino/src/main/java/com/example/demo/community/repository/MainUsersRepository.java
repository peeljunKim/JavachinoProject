package com.example.demo.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Users;

@Repository
public interface MainUsersRepository extends JpaRepository<Users, Integer> {

	public Users findByUsersId(String usersId);
}
