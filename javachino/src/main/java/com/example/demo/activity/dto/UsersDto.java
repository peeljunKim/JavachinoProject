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
}
