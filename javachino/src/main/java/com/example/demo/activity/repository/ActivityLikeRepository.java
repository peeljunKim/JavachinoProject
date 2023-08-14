package com.example.demo.activity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Activity;
import com.example.demo.entity.ActivityLike;
import com.example.demo.entity.Users;

@Repository
public interface ActivityLikeRepository extends JpaRepository<ActivityLike, Integer> {
	
	@Modifying
	@Query(value = "insert into activity_like (activity_no, users_no, activity_like_no) values (?1, ?2, ACTIVITY_LIKE_SEQ.nextval)", nativeQuery = true)
	void insertActivityLike(int activityNo, int usersNo);
	
	public boolean existsByUsersNoAndActivityNo(Users usersNo, Activity activityNo);
	public void deleteByUsersNoAndActivityNo(Users usersNo, Activity activityNo);
}
