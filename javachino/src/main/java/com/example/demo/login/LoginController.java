package com.example.demo.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Member;
import com.example.demo.entity.Users;
import com.example.demo.login.MemberDAO_jpa;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
@Setter
public class LoginController {
	
 	@Autowired
    private JoinService joinService;
    @Autowired
    private LoginService loginService;
	@Autowired
	private MemberDAO_jpa memberdao_jpa;
	
//기존 로그인	

	@GetMapping("/header/original_login")
	public String original_login() {
		return "/header/original_login";
	}
	
	@GetMapping("/header/original_join")
	public String original_join() {
		return "/header/original_join";
	}

//join controller
    @PostMapping(path = "/api/signup")
    public String signUp(@ModelAttribute Users user) {
        // 프로필 사진이 없는 경우에 대한 처리
        if (user.getUsersFname() == null) {
            user.setUsersFname(""); // 기본값 user.img 설정해주기
        }
        joinService.saveUser(user);
        return "redirect:/header/original_login"; // 회원가입 완료 시 로그인 페이지로 리다이렉트
    }
	    
//login controller
    @GetMapping("/login")
    public ModelAndView showLoginForm() {
        ModelAndView modelAndView = new ModelAndView("header/original_login");
        return modelAndView;
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String usersId, @RequestParam String usersPassword, HttpSession session, Model model) {
        Users user = loginService.loginUser(usersId, usersPassword); // LoginService로 로그인 처리

        if (user != null) {
            session.setAttribute("loggedInUserId", user.getUsersId()); // 로그인한 사용자의 ID 저장
           
            // 카카오 로그인 시와 동일한 방식으로 회원 정보를 세션에 저장
            session.setAttribute("m", memberdao_jpa.findByUserId(usersId));
            
            System.out.println("로그인 성공 " + user.getUsersId());
            return "redirect:/activity/activityMain"; // 로그인 성공 시 리다이렉트
        } else {
            System.out.println("로그인 실패");
            model.addAttribute("error", "로그인 실패");
            return "header/original_login"; // 로그인 실패 시 다시 로그인 페이지로
        }
    }
    
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		session.invalidate(); // 로그아웃 시 세션파기
		System.out.println("로그아웃"+loggedInUserId);
		return "redirect:/header/original_login";
	}

//카카오 로그인
	@GetMapping("/login/login")
	public void firstLogin() {
	}

	//로그인 화면
	@GetMapping("/login/activityMain")
	public ModelAndView login(HttpSession session, String id, String password) {
		
		// 카카오 로그인이 아닌 경우는 아직 session으로 값을 넘겨주지 않음
		// 카카오 로그인의 경우 로그인하면서 따로 넘겨줌.
		if(session.getAttribute("m") == null) {
		// 로그인된 회원의 정보를 가져오기 위하여 
		// 시큐리티의 인증 객체 필요
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		  // 위의 인증 객체를 통해 로그인된 user 객체를 받아옴
		  User user = (User) authentication.getPrincipal();
		  
		  // user를 통해 로그인한 회원의 id 가져옴
		  String userid = user.getUsername();
		  Member m = memberdao_jpa.findByUserId(userid);
		  session.setAttribute("m", m);
		  System.out.println(m.getWhere());
		}
		ModelAndView mav = new ModelAndView("redirect:/activity/activityMain");

		return mav;
	}

	
}
