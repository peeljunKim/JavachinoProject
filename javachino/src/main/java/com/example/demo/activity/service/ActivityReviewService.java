package com.example.demo.activity.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.activity.dto.ActivityDto;
import com.example.demo.activity.dto.ActivityReviewDto;
import com.example.demo.activity.repository.ActivityRepository;
import com.example.demo.activity.repository.ActivityReviewRepository;
import com.example.demo.entity.Activity;
import com.example.demo.entity.ActivityReview;

@Service
public class ActivityReviewService {

	private final ActivityReviewRepository activityReviewRepository;
	private final ActivityRepository activityRepository;
	private final ActivityService activityService;

	@Autowired
	public ActivityReviewService(ActivityReviewRepository activityReviewRepository,
			ActivityRepository activityRepository, ActivityService activityService) {
		this.activityReviewRepository = activityReviewRepository;
		this.activityRepository = activityRepository;
		this.activityService = activityService;
	}

	public int getReviewCountForActivity(ActivityDto activityDto) {
		int activityNo = activityDto.getActivityNo();
		Activity activity = activityService.getActivityByActivityNo(activityNo);

		if (activity == null) {
			return 0;
		}
		List<ActivityReview> reviews = activityReviewRepository.findByActivity(activity);
		return reviews.size();
	}

	public List<ActivityReviewDto> getReviewsByActivity_ActivityNo(int activityNo) {
		List<ActivityReview> activityReviewList = activityReviewRepository.findByActivity_ActivityNo(activityNo);

		return activityReviewList.stream().map(ActivityReviewDto::new).collect(Collectors.toList());
	}

	public double getAverageRatingForActivity(ActivityDto activityDto) {
		int activityNo = activityDto.getActivityNo();

		Activity activity = activityService.getActivityByActivityNo(activityNo);

		if (activity == null) {
			return 0.0;
		}

		List<ActivityReview> reviews = activityReviewRepository.findByActivity(activity);
		if (reviews.isEmpty()) {
			return 0.0;
		}

		double totalStars = reviews.stream().mapToDouble(ActivityReview::getActivityReviewStar).sum();

		return totalStars / reviews.size();
	}
}