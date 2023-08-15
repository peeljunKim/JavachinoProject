package com.example.demo.header.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Users;
import com.example.demo.header.service.JoinService;
import com.example.demo.header.service.LoginService;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
@Setter
public class HeaderController {
	
	 	@Autowired
	    private JoinService joinService;
	    @Autowired
	    private LoginService loginService;
	    /*
		@GetMapping("/header/header")
		public String header() {
			return "/header/header";
		}
		*/
	    @GetMapping("/header/header")
	    public String header(HttpSession session, Model model) {
	        String loggedInUserId = (String) session.getAttribute("loggedInUserId");
	        boolean loggedIn = loggedInUserId != null;
	        
	        model.addAttribute("loggedIn", loggedIn);
	        
	        return "header/header";
	    }

		
		@GetMapping("/header/footer")
		public String footer() {
			return "/header/footer";
		}
		
		@GetMapping("/header/login")
		public String login() {
			return "/header/login";
		}
		
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
	    public String processLogin(@RequestParam String usersId, @RequestParam String usersPhone, HttpSession session, Model model) {
	        Users user = loginService.loginUser(usersId, usersPhone); // LoginService로 로그인 처리

	        if (user != null) {
	            session.setAttribute("loggedInUserId", user.getUsersId()); // 로그인한 사용자의 ID 저장
	            System.out.println("로그인 성공 " + user.getUsersId());
	            return "redirect:/mypage/mypageProfile"; // 로그인 성공 시 리다이렉트
	        } else {
	            System.out.println("로그인 실패");
	            model.addAttribute("error", "로그인 실패");
	            return "header/original_login"; // 로그인 실패 시 다시 로그인 페이지로
	        }
	    }


	    @GetMapping("/logout")
	    public String processLogout(HttpSession session) {
	        String loggedInUserId = (String) session.getAttribute("loggedInUserId"); // 로그아웃한 사용자의 ID 가져오기
	        session.invalidate();
	        System.out.println("로그아웃"+loggedInUserId);
	        return "redirect:/header/original_login"; // 로그아웃 시 로그인 페이지로 리다이렉트
	    }
}
