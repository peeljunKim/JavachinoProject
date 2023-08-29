package com.example.demo.admin.dto;

import lombok.Data;

@Data
public class AdminActivityDto {
    private int activityNo;
    private String activityName;
    private String activityAddr;
    private String activityExplanation;
    private double activityPrice;
    private int activityTime;
    private String activityFname1;
    private String activityFname2;
    private String activityFname3;
	public AdminActivityDto(int activityNo, String activityName, String activityAddr, String activityExplanation,
			double activityPrice, int activityTime, String activityFname1, String activityFname2,
			String activityFname3) {
		super();
		this.activityNo = activityNo;
		this.activityName = activityName;
		this.activityAddr = activityAddr;
		this.activityExplanation = activityExplanation;
		this.activityPrice = activityPrice;
		this.activityTime = activityTime;
		this.activityFname1 = activityFname1;
		this.activityFname2 = activityFname2;
		this.activityFname3 = activityFname3;
	}

}
