package com.example.demo.mypage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.View_PayActivity;

@Repository
public interface PayActivityDAO extends JpaRepository<View_PayActivity, Integer> {
    //public List<View_PayActivity> findAllByUsersNo(int usersNo); 
    
    
	@Query(value="select * from view_pay_activity where users_no = ? order by activity_rv_date desc", nativeQuery = true)
    public List<View_PayActivity> findActivityByUsersNo(int usersNo);
	
}
