package com.example.demo.activity.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.activity.dto.ActivityDto;
import com.example.demo.activity.repository.ActivityRepository;
import com.example.demo.entity.Activity;
import com.example.demo.entity.ActivityCategory;

import lombok.Setter;

@Service
@Setter
public class ActivityService {

	@Autowired
	private final ActivityRepository activityRepository;

	public ActivityService(ActivityRepository activityRepository) {
		this.activityRepository = activityRepository;
	}

	public List<ActivityDto> findAll() {
		List<ActivityDto> activityDtoList = new ArrayList<>();
		List<Activity> activityList = activityRepository.findAll();
		for (Activity activity : activityList) {
			ActivityDto activityDto = new ActivityDto(activity);
			activityDtoList.add(activityDto);
		}
		return activityDtoList;
	}

	public ActivityDto getPost(int activityNo) {
		Activity activity = activityRepository.findById(activityNo)
				.orElseThrow(() -> new NullPointerException("해당 게시글 없음"));
		return new ActivityDto(activity);
	}

	public List<ActivityDto> filterActivitiesByCategory(ActivityCategory activityCategory) {
	    List<Activity> filteredActivities = activityRepository.findByActivityCategory(activityCategory);
	    List<ActivityDto> filteredActivityDtoList = new ArrayList<>();
	    
	    for (Activity activity : filteredActivities) {
	        ActivityDto activityDto = new ActivityDto(activity);
	        filteredActivityDtoList.add(activityDto);
	    }
	    
	    return filteredActivityDtoList;
	}

	public Activity getActivityByActivityNo(int activityNo) {
		return activityRepository.findById(activityNo).orElse(null);
	}
	
	public List<ActivityDto> findActivitiesByActivityAddr(String activityAddr) {
	    List<Activity> activities = activityRepository.findByActivityAddrContaining(activityAddr);
	    return activities.stream().map(ActivityDto::new).collect(Collectors.toList());
	}
}
