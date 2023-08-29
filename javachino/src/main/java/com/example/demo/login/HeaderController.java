package com.example.demo.login;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Users;
import com.example.demo.mypage.service.MyUsersService;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
@Setter
public class HeaderController {
	
	 	@Autowired
	    private JoinService joinService;
	    @Autowired
	    private LoginService loginService;
	    @Autowired
	    private MyUsersService myusersservice;

	    @GetMapping("/header/mainpage")
	    public String headerMain() {
	    	return "main/mainpage";
	    }
	    
	    @GetMapping("/accomodation/accomodationMain1")
	   public String headerAccomodation() {
		   return "accomodation/accomodationMain";
	   }
	    
	    @GetMapping("/header/activity")
	    public String headerActivity() {
	    	return "activity/activityMain";
	    }
	    
	    @GetMapping("/main/board2")
	    public String headerCommunity() {
	    	return "main/board2";
	    }

	    /*
	    @GetMapping("/header/header")
	    public String header(HttpSession session, Model model) {
	        String loggedInUserId = (String) session.getAttribute("loggedInUserId");
	        boolean loggedIn = loggedInUserId != null;
	        
	        model.addAttribute("loggedIn", loggedIn);
	        
	        return "header/header";
	    }
	    */
	    
	    @GetMapping("/header/header")
	    public String header(HttpSession session, Model model, OAuth2AuthenticationToken authentication) {
	        // 카카오로부터 받은 사용자 정보를 가져오기
	        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();

	        // KakaoUserInfo 클래스를 사용하여 카카오 사용자 정보를 가져옴
	        KakaoUserInfo kakaoUserInfo = KakaoUserInfo.ofKakao("kakao", "id", oauth2User.getAttributes());

	        String loggedInUserId = kakaoUserInfo.getEmail();

	        boolean loggedIn = loggedInUserId != null;

	        model.addAttribute("loggedIn", loggedIn);

	        if (loggedIn) {
	            // 세션에 사용자 정보 저장 
	            session.setAttribute("loggedInUserEmail", loggedInUserId);
	            session.setAttribute("loggedInUserAttributes", oauth2User.getAttributes());
	        
	            // 세션에 사용자 정보로 마이페이지를 열기 위한 정보 저장
	            Optional<Users> userOptional = myusersservice.getUserByUsersId(loggedInUserId);
	            if (userOptional.isPresent()) {
	                Users user = userOptional.get();
	                session.setAttribute("loggedInUser", user);
	            }
	        }

	        System.out.println("OAuth2 User Attributes: " + oauth2User.getAttributes());
	        System.out.println("header/header loggedInUserId: " + loggedInUserId);

	        return "header/header";
	    }

		
		@GetMapping("/header/footer")
		public String footer() {
			return "/header/footer";
		}
		
}
