package com.example.demo.activity.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersDto {
	private int usersNo;
	private String usersId;
	private String usersName;
	private String usersFname;
	private String usersPhone;
	private Date userDate;
	private String usersPassword;
	
	public UsersDto(int usersNo, String usersId, String usersName, String usersFname, String usersPhone, String usersPassword) {
		this.usersNo = usersNo;
		this.usersId = usersId;
		this.usersName = usersName;
		this.usersFname = usersFname;
		this.usersPhone = usersPhone;
		this.usersPassword = usersPassword;
	}

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

	public Date getUserDate() {
		return userDate;
	}

	public void setUserDate(Date userDate) {
		this.userDate = userDate;
	}

	public String getUsersPassword() {
		return usersPassword;
	}

	public void setUsersPassword(String usersPassword) {
		this.usersPassword = usersPassword;
	}
	
	
}
