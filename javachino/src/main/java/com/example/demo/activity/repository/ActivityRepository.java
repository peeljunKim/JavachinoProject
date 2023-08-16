package com.example.demo.activity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Activity;
import com.example.demo.entity.ActivityCategory;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {
	Activity findByActivityNo(int activityNo);

	List<Activity> findByActivityCategory(ActivityCategory activityCategory);
	List<Activity> findByActivityAddrContaining(String activityAddr);
}
