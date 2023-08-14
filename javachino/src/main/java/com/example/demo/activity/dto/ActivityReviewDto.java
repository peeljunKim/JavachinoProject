package com.example.demo.activity.dto;

import java.time.LocalDate;

import com.example.demo.entity.ActivityReview;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityReviewDto {
	private int activityReviewNo;
	private double activityReviewStar;
	private String activityReviewContent;
	private LocalDate activityReviewDate;
	private UsersDto usersDto;
	private ActivityDto activityDto;
	
	public ActivityReviewDto(ActivityReview activityReview) {
        this.activityReviewNo = activityReview.getActivityReviewNo();
        this.activityReviewStar = activityReview.getActivityReviewStar();
        this.activityReviewContent = activityReview.getActivityReviewContent();
        this.activityReviewDate = activityReview.getActivityReviewDate();
        this.usersDto = new UsersDto(
            activityReview.getUsers().getUsersNo(),
            activityReview.getUsers().getUsersId(),
            activityReview.getUsers().getUsersName(),
            activityReview.getUsers().getUsersFname(),
            activityReview.getUsers().getUsersPhone(),
            activityReview.getUsers().getUsersPassword() // Include password here
        );
        if (activityReview.getActivity() != null) {
            this.activityDto = new ActivityDto(activityReview.getActivity());
        }
    }
}
