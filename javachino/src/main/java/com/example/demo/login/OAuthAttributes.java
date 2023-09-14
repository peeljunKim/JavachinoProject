package com.example.demo.login;

import java.util.Map;
import java.util.Random;

import com.example.demo.entity.Member;
import com.example.demo.login.KakaoUserInfo;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@RequiredArgsConstructor
public class OAuthAttributes {
	private final Map<String, Object> attributes;
    private final String nameAttributeKey;
    private final String name;
    private final String email;
    private final String gender;
    private final String nickname;
    private final String mobile;
    private final String birthyear;
    private final String birthday;
    private final String where;
    
    
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email,
            String gender, String nickname, String mobile, String birthyear, String birthday, String where) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.nickname = nickname;
		this.mobile = mobile;
		this.birthyear = birthyear;
		this.birthday = birthday;
		this.where = where;
		}

    public static OAuthAttributes ofNaver(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        
        return new OAuthAttributes(
                attributes,
                userNameAttributeName,
                (String) response.get("name"),
                (String) response.get("email"),
                (String) response.get("gender"),
                (String) response.get("nickname"),
                (String) response.get("mobile"),
                (String) response.get("birthyear"),
                (String) response.get("birthday"),
                registrationId
            );
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

	public String getNickname() {
		return nickname;
	}

	public String getMobile() {
		return mobile;
	}

	public String getBirthyear() {
		return birthyear;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getWhere() {
		return where;
	}
    
    
    
}
