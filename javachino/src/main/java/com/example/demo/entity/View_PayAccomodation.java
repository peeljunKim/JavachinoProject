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