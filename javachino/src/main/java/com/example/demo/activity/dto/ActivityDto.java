package com.example.demo.activity.dto;

import java.text.DecimalFormat;

import com.example.demo.entity.Activity;
import com.example.demo.entity.ActivityCategory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityDto {
	private int activityNo;
	private String activityName;
	private String activityAddr;
	private String activityExplanation;
	private ActivityCategory activityCategory;
	private double activityPrice;
	private int activityTime;
	private String activityFname1;
	private String activityFname2;
	private String activityFname3;
	private BusinessDto businessDto;

	// 별점 위해서 추가
	private double averageRating;

	// 별점 매긴 사람 총 합 구하기 위해서 추가
	private int reviewCount;

	 public ActivityDto(Activity activity) {
	        this.activityNo = activity.getActivityNo();
	        this.activityName = activity.getActivityName();
	        this.activityAddr = activity.getActivityAddr();
	        this.activityExplanation = activity.getActivityExplanation();
	        this.activityCategory = activity.getActivityCategory();
	        this.activityPrice = activity.getActivityPrice();
	        this.activityTime = activity.getActivityTime();
	        this.activityFname1 = activity.getActivityFname1();
	        this.activityFname2 = activity.getActivityFname2();
	        this.activityFname3 = activity.getActivityFname3();
	        this.businessDto = new BusinessDto(activity.getBusinessNo().getBusinessNo(),
	                activity.getBusinessNo().getBusinessName(), activity.getBusinessNo().getBusinessAddr(),
	                activity.getBusinessNo().getBusinessPhone(), activity.getBusinessNo().getBusinessManager(),
	                activity.getBusinessNo().getBusinessCategory());

	        // 소수점 이하 제거
	        DecimalFormat decimalFormat = new DecimalFormat("#");
	        this.activityPrice = Double.parseDouble(decimalFormat.format(this.activityPrice));
	    }

	public int getActivityNo() {
		return activityNo;
	}

	public void setActivityNo(int activityNo) {
		this.activityNo = activityNo;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityAddr() {
		return activityAddr;
	}

	public void setActivityAddr(String activityAddr) {
		this.activityAddr = activityAddr;
	}

	public String getActivityExplanation() {
		return activityExplanation;
	}

	public void setActivityExplanation(String activityExplanation) {
		this.activityExplanation = activityExplanation;
	}

	public ActivityCategory getActivityCategory() {
		return activityCategory;
	}

	public void setActivityCategory(ActivityCategory activityCategory) {
		this.activityCategory = activityCategory;
	}

	public double getActivityPrice() {
		return activityPrice;
	}

	public void setActivityPrice(double activityPrice) {
		this.activityPrice = activityPrice;
	}

	public int getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(int activityTime) {
		this.activityTime = activityTime;
	}

	public String getActivityFname1() {
		return activityFname1;
	}

	public void setActivityFname1(String activityFname1) {
		this.activityFname1 = activityFname1;
	}

	public String getActivityFname2() {
		return activityFname2;
	}

	public void setActivityFname2(String activityFname2) {
		this.activityFname2 = activityFname2;
	}

	public String getActivityFname3() {
		return activityFname3;
	}

	public void setActivityFname3(String activityFname3) {
		this.activityFname3 = activityFname3;
	}

	public BusinessDto getBusinessDto() {
		return businessDto;
	}

	public void setBusinessDto(BusinessDto businessDto) {
		this.businessDto = businessDto;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	 
	 
	}
	

