package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "view_like_accomodation")
public class View_LikeAccomodation {

	@Id
	@Column(name = "users_no")
	private int usersNo;
	@Column(name = "accomodation_no")
	private int accomodationNo;
	@Column(name = "accomodation_name")
	private String accomodationName;
	@Column(name = "accomodation_review_star")
	private float accomodationReviewStar;
	public int getUsersNo() {
		return usersNo;
	}
	public void setUsersNo(int usersNo) {
		this.usersNo = usersNo;
	}
	public int getAccomodationNo() {
		return accomodationNo;
	}
	public void setAccomodationNo(int accomodationNo) {
		this.accomodationNo = accomodationNo;
	}
	public String getAccomodationName() {
		return accomodationName;
	}
	public void setAccomodationName(String accomodationName) {
		this.accomodationName = accomodationName;
	}
	public float getAccomodationReviewStar() {
		return accomodationReviewStar;
	}
	public void setAccomodationReviewStar(float accomodationReviewStar) {
		this.accomodationReviewStar = accomodationReviewStar;
	}
	
	
	
}


/*
CREATE OR REPLACE VIEW view_like_accomodation AS
SELECT
    al.users_no,
    al.accomodation_no,
    a.accomodation_name,
    ar.accomodation_review_star
FROM
    accomodation_like al
JOIN
    accomodation a ON al.accomodation_no = a.accomodation_no
JOIN
    accomodation_review ar ON al.accomodation_no = ar.accomodation_no;

*/