package com.example.demo.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Activity;

@Repository
public interface AdminActivityRepository extends JpaRepository<Activity, Integer> {
    Page<Activity> findAll(Pageable pageable);
    
    //activityName으로 검색
    Page<Activity> findByActivityNameContaining(String activityName, Pageable pageable);
    
    //activityAddr로 검색
    Page<Activity> findByActivityAddrContaining(String activityAddr, Pageable pageable);
}
