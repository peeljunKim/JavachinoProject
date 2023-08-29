package com.example.demo.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Users;

@Repository
public interface K_UsersRepository extends JpaRepository<Users, Integer> {

	public Users save(Users users);
}
