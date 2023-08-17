package com.example.demo.admin.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ActivityDto {
	private int ActivityNo;
	private String ActivityName;
	public ActivityDto(int activityNo, String activityName) {
		super();
		ActivityNo = activityNo;
		ActivityName = activityName;
	}
	

	
	
	
	
}
