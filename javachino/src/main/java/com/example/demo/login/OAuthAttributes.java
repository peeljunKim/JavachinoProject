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
    

    public static OAuthAttributes ofNaver(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        
        return OAuthAttributes.builder()
        		.attributes(attributes)
                .nickname((String) response.get("nickname"))
                .email((String) response.get("email"))
                .gender((String) response.get("gender"))
                .name((String) response.get("name"))
                .mobile((String) response.get("mobile"))
                .birthyear((String) response.get("birthyear"))
                .birthday((String) response.get("birthday"))
                .where(registrationId)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }
    
}
