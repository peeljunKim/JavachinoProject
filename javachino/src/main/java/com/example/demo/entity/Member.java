package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
@Table(name="member")
public class Member {
	@Id
	private String id;	
	private String pwd;
	private String name;
	private String birth;
	private String email;
	private String gender;  //'남성', '여성'
	private String role;    //'admin','user'
	private String phone; // 숫자만
	private String nickname; // 6자 이하
	private String fname; // id.jpg
	
	@Transient
	private String where; // kakao, naver, google
	
	
	//카카오
	@Builder(builderMethodName = "builderKakao")
    public Member(String id, String email, String gender, String birth, String where) {
        System.out.println("카카오로옴Member.java");
		String userid = email.substring(0, email.indexOf("@"))+"_kakao";
		this.id = userid;
        this.pwd = "tripjava";
        this.email = email;
        this.name = id;
        
        String newnickname = nickname;

        Random r = new Random();
        int a = r.nextInt(10);
        int b = r.nextInt(10);
        int c = r.nextInt(10);
        int d = r.nextInt(10);
        newnickname += a+""+b+""+c+""+d+"";
        this.nickname = newnickname;
        this.phone = "010-0000-0000";
        this.fname = "user.png";
        this.role = "user";
        if(gender.equals("female")) {
        	this.gender = "여성";
        }
        else {
        	this.gender = "남성";
        }
        if(birth != null) {
            birth = birth.substring(0, 2)+"-"+birth.substring(2, 4);
        }
        else {
        	birth = "해당 정보가 없습니다.";
        }
        this.birth = birth;
        this.where = where;
    }


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getBirth() {
		return birth;
	}


	public void setBirth(String birth) {
		this.birth = birth;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public String getFname() {
		return fname;
	}


	public void setFname(String fname) {
		this.fname = fname;
	}


	public String getWhere() {
		return where;
	}


	public void setWhere(String where) {
		this.where = where;
	}


	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
