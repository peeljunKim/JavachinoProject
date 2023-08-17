package com.example.demo.community.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ActivityRv;

import jakarta.transaction.Transactional;

@Repository
public interface MainActivityRvRepository extends JpaRepository<ActivityRv, Integer> {
    
    @Query(value="select * from activity_rv where users_no = :usersNo", nativeQuery = true)
    public List<ActivityRv> findAllByUsersNo(int usersNo);
    
    public ActivityRv findByActivityRvNo(int activityRvNo);
    
}
