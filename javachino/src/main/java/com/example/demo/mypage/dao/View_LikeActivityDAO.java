package com.example.demo.mypage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface View_LikeActivityDAO extends JpaRepository<com.example.demo.entity.View_LikeActivity, Integer> {

}
