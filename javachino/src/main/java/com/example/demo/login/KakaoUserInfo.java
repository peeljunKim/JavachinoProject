package com.example.demo.login;


import java.util.Date;
import java.util.Map;

import com.example.demo.entity.Member;
import com.example.demo.entity.Users;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class KakaoUserInfo {
	private final Map<String, Object> attributes; // OAuth2 반환하는 유저 정보 Map
    private final String nameAttributeKey;
    private final String name;
    private final String email;
    private final String gender;
    private final String birthday;
    private final String where;

    public static KakaoUserInfo of(String registrationId, Map<String, Object> attributes) {
        return ofKakao(registrationId, "id", attributes);
    }


    public static KakaoUserInfo ofKakao(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
    	// kakao는 kakao_account에 유저정보가 있다. (email)
        Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get("kakao_account");
        // kakao_account안에 또 profile이라는 JSON객체가 있다. (nickname, profile_image)
        Map<String, Object> kakaoProfile = (Map<String, Object>)kakaoAccount.get("profile");

        return new KakaoUserInfo(
                attributes,
                userNameAttributeName,
                (String) kakaoProfile.get("nickname"),
                (String) kakaoAccount.get("email"),
                (String) kakaoAccount.get("gender"),
                (String) kakaoAccount.get("birthday"),
                registrationId
            );
    }

    public Member toEntity(){
    	 Member member = new Member();
    	    member.setId(name);
    	    member.setEmail(email);
    	    member.setGender(gender);
    	    member.setBirth(birthday);
    	    member.setWhere(where);
    	    member.setRole("user");
    	    return member;
    }
    
    public Users toUsersEntity() {
    	Users users = new Users();
        users.setUsersId(email);
        users.setUsersPassword("tripjava");
        users.setUsersName(name);
        users.setUsersFname("user.png");
        users.setUsersDate(new Date());
        users.setUsersPhone("010-1234-5678");
        return users;
    			
    }


	public Map<String, Object> getAttributes() {
		return attributes;
	}


	public String getNameAttributeKey() {
		return nameAttributeKey;
	}


	public String getName() {
		return name;
	}


	public String getEmail() {
		return email;
	}


	public String getGender() {
		return gender;
	}


	public String getBirthday() {
		return birthday;
	}


	public String getWhere() {
		return where;
	}


	public KakaoUserInfo(Map<String, Object> attributes, String nameAttributeKey, String name, String email,
			String gender, String birthday, String where) {
		super();
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.birthday = birthday;
		this.where = where;
	}
    
    
}