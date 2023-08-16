package com.example.demo.entity;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "users")
@SequenceGenerator(
        name="USERS_SEQ_GEN",
        sequenceName = "USERS_SEQ",
    	initialValue = 1,
    	allocationSize = 1
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users {
    @Id
    @GeneratedValue(
		strategy =  GenerationType.SEQUENCE,
		generator = "USERS_SEQ_GEN"
    )
    @Column(name = "users_no")
    private int usersNo;
    @Column(name = "users_id")
    private String usersId;
    @Column(name = "users_password")
    private String usersPassword;
    @Column(name = "users_name")
    private String usersName;
    @Column(name = "users_fname")
    private String usersFname;
    //테이블 매핑에서는 제외 시켜라!
  	@Transient
  	private MultipartFile uploadFile;
    @Column(name = "users_phone")
    private String usersPhone;
    @Column(name = "users_date")
    private Date usersDate;
	public int getUsersNo() {
		return usersNo;
	}
	public void setUsersNo(int usersNo) {
		this.usersNo = usersNo;
	}
	public String getUsersId() {
		return usersId;
	}
	public void setUsersId(String usersId) {
		this.usersId = usersId;
	}
	public String getUsersPassword() {
		return usersPassword;
	}
	public void setUsersPassword(String usersPassword) {
		this.usersPassword = usersPassword;
	}
	public String getUsersName() {
		return usersName;
	}
	public void setUsersName(String usersName) {
		this.usersName = usersName;
	}
	public String getUsersFname() {
		return usersFname;
	}
	public void setUsersFname(String usersFname) {
		this.usersFname = usersFname;
	}
	public String getUsersPhone() {
		return usersPhone;
	}
	public void setUsersPhone(String usersPhone) {
		this.usersPhone = usersPhone;
	}
	public Date getUsersDate() {
		return usersDate;
	}
	public void setUsersDate(Date usersDate) {
		this.usersDate = usersDate;
	}
    
} 
