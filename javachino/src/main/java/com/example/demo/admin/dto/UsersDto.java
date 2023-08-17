package com.example.demo.admin.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UsersDto {
	private int usersNo;
	private String usersName;
	public UsersDto(int usersNo, String usersName) {
		super();
		this.usersNo = usersNo;
		this.usersName = usersName;
	}

	
	
	
	
}
