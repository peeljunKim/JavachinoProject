package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "view_like_activity")
public class View_LikeActivity {

	@Id
	@Column(name = "users_no")
	private int usersNo;
	@Column(name = "activity_no")
	private int activityNo;
	@Column(name = "activity_name")
	private String activityName;
	@Column(name = "activity_fname1")
	private String activityFname1;
	@Column(name = "activity_price")
	private float activityPrice;
	@Column(name = "review_count")
	private int reviewCount;
	@Column(name = "avg_review_star")
	private double avgReviewStar;
	public int getUsersNo() {
		return usersNo;
	}
	public void setUsersNo(int usersNo) {
		this.usersNo = usersNo;
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
	public String getActivityFname1() {
		return activityFname1;
	}
	public void setActivityFname1(String activityFname1) {
		this.activityFname1 = activityFname1;
	}
	public float getActivityPrice() {
		return activityPrice;
	}
	public void setActivityPrice(float activityPrice) {
		this.activityPrice = activityPrice;
	}
	public int getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	public double getAvgReviewStar() {
		return avgReviewStar;
	}
	public void setAvgReviewStar(double avgReviewStar) {
		this.avgReviewStar = avgReviewStar;
	}
	
	
}

/*
CREATE or replace VIEW view_like_activity AS
SELECT 
    al.users_no,
    a.activity_no,
    a.activity_name,
    a.activity_fname1,
    COUNT( ar.activity_review_star) AS review_count,
    AVG( ar.activity_review_star) AS avg_review_star
FROM
    activity a
INNER JOIN
    activity_like al
ON
    a.activity_no = al.activity_no
INNER JOIN
    activity_review ar
ON
    a.activity_no = ar.activity_no
GROUP BY
    al.users_no,
    a.activity_no,
    a.activity_name,
    a.activity_fname1;
*/