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
            activityReview.getUsers().getUsersPassword() 
        );
        if (activityReview.getActivity() != null) {
            this.activityDto = new ActivityDto(activityReview.getActivity());
        }
    }

	public int getActivityReviewNo() {
		return activityReviewNo;
	}

	public void setActivityReviewNo(int activityReviewNo) {
		this.activityReviewNo = activityReviewNo;
	}

	public double getActivityReviewStar() {
		return activityReviewStar;
	}

	public void setActivityReviewStar(double activityReviewStar) {
		this.activityReviewStar = activityReviewStar;
	}

	public String getActivityReviewContent() {
		return activityReviewContent;
	}

	public void setActivityReviewContent(String activityReviewContent) {
		this.activityReviewContent = activityReviewContent;
	}

	public LocalDate getActivityReviewDate() {
		return activityReviewDate;
	}

	public void setActivityReviewDate(LocalDate activityReviewDate) {
		this.activityReviewDate = activityReviewDate;
	}

	public UsersDto getUsersDto() {
		return usersDto;
	}

	public void setUsersDto(UsersDto usersDto) {
		this.usersDto = usersDto;
	}

	public ActivityDto getActivityDto() {
		return activityDto;
	}

	public void setActivityDto(ActivityDto activityDto) {
		this.activityDto = activityDto;
	}
	
	
}
