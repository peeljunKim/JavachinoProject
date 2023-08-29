package com.example.demo.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ActivityPayVO {

	private int activity_no;
	private String activity_name;
	private String activity_fname1;
	private Date activity_rv_date;
	private Date activity_review_date;
	private int pay_price;
	private int pay_no;
	private double activity_review_star;
}
