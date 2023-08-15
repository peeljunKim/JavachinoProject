package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "view_pay_activity")
public class View_PayActivity {

	@Id
	@Column(name = "users_no")
	private int usersNo;
	@Column(name = "pay_no")
	private int payNo;
	@Column(name = "pay_price")
	private int payPrice;
	@Column(name = "activity_rv_no")
	private int activityRvNo;
	@Column(name = "activity_rv_date")
	private Date activityRvDate;
	@Column(name = "activity_review_date")
	private Date activityReviewDate;
	@Column(name = "activity_no")
	private int activityNo;
	@Column(name = "activity_name")
    private String activityName;
	@Column(name = "activity_fname1")
    private String activityFname1;
	@Column(name = "activity_review_star")
    private Double activityReviewStar;
	public int getUsersNo() {
		return usersNo;
	}
	public void setUsersNo(int usersNo) {
		this.usersNo = usersNo;
	}
	public int getPayNo() {
		return payNo;
	}
	public void setPayNo(int payNo) {
		this.payNo = payNo;
	}
	public int getPayPrice() {
		return payPrice;
	}
	public void setPayPrice(int payPrice) {
		this.payPrice = payPrice;
	}
	public int getActivityRvNo() {
		return activityRvNo;
	}
	public void setActivityRvNo(int activityRvNo) {
		this.activityRvNo = activityRvNo;
	}
	public Date getActivityRvDate() {
		return activityRvDate;
	}
	public void setActivityRvDate(Date activityRvDate) {
		this.activityRvDate = activityRvDate;
	}
	public Date getActivityReviewDate() {
		return activityReviewDate;
	}
	public void setActivityReviewDate(Date activityReviewDate) {
		this.activityReviewDate = activityReviewDate;
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
	public Double getActivityReviewStar() {
		return activityReviewStar;
	}
	public void setActivityReviewStar(Double activityReviewStar) {
		this.activityReviewStar = activityReviewStar;
	}
	
	
}


/*
CREATE or replace VIEW view_pay_activity AS
SELECT
    ar.users_no,
    p.PAY_NO,
    p.PAY_PRICE,
    ar.ACTIVITY_RV_NO,
    ar.ACTIVITY_RV_DATE,
    ar.ACTIVITY_NO,
    rv.ACTIVITY_REVIEW_DATE,
 	rv.ACTIVITY_REVIEW_STAR,
    a.ACTIVITY_NAME,
    a.ACTIVITY_FNAME1
FROM
    PAY p
JOIN
	ACTIVITY_RV ar 
ON 
	p.ACTIVITY_RV_NO = ar.ACTIVITY_RV_NO
JOIN 
	ACTIVITY a 
ON 
	ar.ACTIVITY_NO = a.ACTIVITY_NO
LEFT JOIN 
	ACTIVITY_REVIEW rv 
ON 
	ar.ACTIVITY_NO = rv.ACTIVITY_NO and ar.users_no = rv.user_no
GROUP BY
	ar.users_no,
    p.PAY_NO,
    p.PAY_PRICE,
    ar.ACTIVITY_RV_NO,
    ar.ACTIVITY_RV_DATE,
    ar.ACTIVITY_NO,
    rv.ACTIVITY_REVIEW_DATE,
 	rv.ACTIVITY_REVIEW_STAR,
    a.ACTIVITY_NAME,
    a.ACTIVITY_FNAME1
	;


*/