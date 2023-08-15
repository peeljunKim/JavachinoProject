package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "view_pay_accomodation")
public class View_PayAccomodation {

	@Id
	private int users_no;
	private int pay_no;
	private int pay_price;
	private int accomodation_rv_no;
	private Date accomodation_rv_date;
	private Date accomodation_review_date;
	private int accomodation_no;
    private String accomodation_name;
    private String accomodation_file_fname;
    private double accomodation_review_star;
	public int getUsers_no() {
		return users_no;
	}
	public void setUsers_no(int users_no) {
		this.users_no = users_no;
	}
	public int getPay_no() {
		return pay_no;
	}
	public void setPay_no(int pay_no) {
		this.pay_no = pay_no;
	}
	public int getPay_price() {
		return pay_price;
	}
	public void setPay_price(int pay_price) {
		this.pay_price = pay_price;
	}
	public int getAccomodation_rv_no() {
		return accomodation_rv_no;
	}
	public void setAccomodation_rv_no(int accomodation_rv_no) {
		this.accomodation_rv_no = accomodation_rv_no;
	}
	public Date getAccomodation_rv_date() {
		return accomodation_rv_date;
	}
	public void setAccomodation_rv_date(Date accomodation_rv_date) {
		this.accomodation_rv_date = accomodation_rv_date;
	}
	public Date getAccomodation_review_date() {
		return accomodation_review_date;
	}
	public void setAccomodation_review_date(Date accomodation_review_date) {
		this.accomodation_review_date = accomodation_review_date;
	}
	public int getAccomodation_no() {
		return accomodation_no;
	}
	public void setAccomodation_no(int accomodation_no) {
		this.accomodation_no = accomodation_no;
	}
	public String getAccomodation_name() {
		return accomodation_name;
	}
	public void setAccomodation_name(String accomodation_name) {
		this.accomodation_name = accomodation_name;
	}
	public String getAccomodation_file_fname() {
		return accomodation_file_fname;
	}
	public void setAccomodation_file_fname(String accomodation_file_fname) {
		this.accomodation_file_fname = accomodation_file_fname;
	}
	public double getAccomodation_review_star() {
		return accomodation_review_star;
	}
	public void setAccomodation_review_star(double accomodation_review_star) {
		this.accomodation_review_star = accomodation_review_star;
	}
    
    
}

/*
CREATE VIEW view_pay_accomodation AS
SELECT
    p.PAY_NO AS users_no,
    p.PAY_NO,
    p.PAY_PRICE,
    ar.ACCOMODATION_RV_NO,
    ar.ACCOMODATION_RV_DATE,
    rv.ACCOMODATION_REVIEW_DATE,
    ar.ACCOMODATION_NO,
    a.ACCOMODATION_NAME,
    af.ACCOMODATION_FILE_FNAME,
    rv.ACCOMODATION_REVIEW_STAR
FROM
    PAY p
    JOIN ACCOMODATION_RV ar ON p.ACCOMODATION_RV_NO = ar.ACCOMODATION_RV_NO
    JOIN ACCOMODATION a ON ar.ACCOMODATION_NO = a.ACCOMODATION_NO
    LEFT JOIN ACCOMODATION_REVIEW rv ON ar.ACCOMODATION_NO = rv.ACCOMODATION_NO
    LEFT JOIN ACCOMODATION_FILE af ON a.ACCOMODATION_NO = af.ACCOMODATION_NO;

*/