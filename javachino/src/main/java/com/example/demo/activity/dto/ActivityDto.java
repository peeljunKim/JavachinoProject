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
	}
	

