package com.example.demo.login;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.demo.accomodation.repository.UsersRepository;
import com.example.demo.entity.Member;
import com.example.demo.entity.Users;
import com.example.demo.login.KakaoUserInfo;
import com.example.demo.login.MemberDAO_jpa;
import com.example.demo.login.OAuthAttributes;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>  {
	@Autowired
	private MemberDAO_jpa memberdao_jpa;
	
	@Autowired
	private K_UsersRepository k_usersRepository;
	
    private final HttpSession httpSession;
    
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        
        Member m = new Member();
        
        if(registrationId.equals("kakao")) {
        	// OAuth2 로그인 진행 시 키가 되는 필드 값(PK) // id
            String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
            // OAuth2UserService
            KakaoUserInfo attributes = KakaoUserInfo.ofKakao(registrationId, userNameAttributeName, oAuth2User.getAttributes());
            if(memberdao_jpa.countByNameAndEmail(attributes.getWhere(), attributes.getName(), attributes.getEmail()) == 0) {
            	m = saveOrUpdateKakao(attributes);
                httpSession.setAttribute("m", m); 
            }
            
            else {
            	m = memberdao_jpa.findByNameAndEmail(attributes.getWhere(), attributes.getName(), attributes.getEmail());
            	httpSession.setAttribute("m", m);
            }
            return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(m.getRole())),
                    attributes.getAttributes(),
                    attributes.getNameAttributeKey());
        }
     // 기본적으로 null 반환
        return null;
    }

    
 // 유저 생성 및 수정 서비스 로직 (카카오)
    @Transactional
    public Member saveOrUpdateKakao(KakaoUserInfo attributes){
        Member m = attributes.toEntity();
        memberdao_jpa.save(m);
        
        Users users = attributes.toUsersEntity();
        k_usersRepository.save(users);
        
        return m;
    }

}
