package com.example.demo.admin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Users;

@Repository
public interface AdminUsersRepository extends JpaRepository<Users, Integer> {
    Page<Users> findAll(Pageable pageable);
    
    //usersId로 검색
    Page<Users> findByUsersIdContaining(String usersId, Pageable pageable);
    
    //usersName로 검색
    Page<Users> findByUsersNameContaining(String usersName, Pageable pageable);
}
