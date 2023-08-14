package com.example.demo.mypage.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Users;
import com.example.demo.entity.View_LikeActivity;
import com.example.demo.entity.View_PayAccomodation;
import com.example.demo.entity.View_PayActivity;
import com.example.demo.mypage.service.MyUsersService;
import com.example.demo.mypage.service.PayAccomodationService;
import com.example.demo.mypage.service.PayActivityService;
import com.example.demo.mypage.service.View_LikeActivityService;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;



@Controller
@Setter
public class MypageController {

		@Autowired
		private MyUsersService us;
		@Autowired
		private View_LikeActivityService las;
		@Autowired
		private PayAccomodationService pas;
	    @Autowired
	    private PayActivityService pActs;
	    

		  //mypageProfile controller
		    @GetMapping("/mypage/mypageProfile")
		    public String mypageProfile(Model model, HttpSession session) {
		        String loggedInUsersId = (String) session.getAttribute("loggedInUserId");
		        if (loggedInUsersId != null) {
		            Optional<Users> userOptional = us.getUserByUsersId(loggedInUsersId);
		            if (userOptional.isPresent()) {
		                Users user = userOptional.get();
		                model.addAttribute("u", user);
		                return "/mypage/mypageProfile";
		            }
		        }
		        // 로그인되지 않았거나 사용자 정보가 없는 경우
		        return "redirect:/header/original_login"; // 로그인 페이지로 리다이렉트
		    }

		    
		    
		//MypageWishlist Controller
		    /*
		    @GetMapping("/mypage/mypageWishlist")
		    public String mypageWishlist (Model model, HttpSession session) {
		        String loggedInUsersId = (String) session.getAttribute("loggedInUserId");
		        if(loggedInUsersId != null) {
		            Optional<Users> userOptional = us.getUserByUsersId(loggedInUsersId);		            
		            if(userOptional.isPresent()) {
		                Users user = userOptional.get();
		                model.addAttribute("u", user);
		                return "mypage/mypageWishlist";
		            }
		        }
		        return "redirect:/header/original_login";
		     }
		     */
			
		    @GetMapping("/mypage/mypageWishlist_Accomodation")
		    public String mypageWishlistAcc (Model model,HttpSession session) {
		        String loggedInUsersId = (String) session.getAttribute("loggedInUserId");
		        if(loggedInUsersId != null) {
		            Optional<Users> userOptional = us.getUserByUsersId(loggedInUsersId);		            
		            if(userOptional.isPresent()) {
		                Users user = userOptional.get();
		                model.addAttribute("u", user);
		                
		                
		        		List<View_LikeActivity> likeActList = las.findAll();
		        		model.addAttribute("likeActList",likeActList);
		                
		                return "/mypage/mypageWishlist_Accomodation";
		            }
		        }
		        return "redirect:/header/original_login";
		     }
		    
			@GetMapping("/mypage/mypageWishlist_Activity")
			public String mypageWishlistAct(Model model, HttpSession session) {
		        String loggedInUsersId = (String) session.getAttribute("loggedInUserId");
		        if(loggedInUsersId != null) {
		            Optional<Users> userOptional = us.getUserByUsersId(loggedInUsersId);		            
		            if(userOptional.isPresent()) {
		                Users user = userOptional.get();
		                model.addAttribute("u", user);
		                return "/mypage/mypageWishlist_Activity";
		            }
		        }
		        return "redirect:/header/original_login";
			}
			
			
		//MypageTravel Controller
			/*
			@GetMapping("/mypage/mypageTravel")
			public String list(Model model,HttpSession session) {
		        String loggedInUsersId = (String) session.getAttribute("loggedInUserId");
		        if(loggedInUsersId != null) {
		            Optional<Users> userOptional = us.getUserByUsersId(loggedInUsersId);		            
		            if(userOptional.isPresent()) {
		                Users user = userOptional.get();
		                model.addAttribute("u", user);
		                return "mypage/mypageTravel";
		            }
		        }
		        return "redirect:/header/original_login";
			}
			*/
			
			@GetMapping("/mypage/mypageTravel_Accomodation")
			public String list1(Model model, HttpSession session) {
		        String loggedInUsersId = (String) session.getAttribute("loggedInUserId");
		        if(loggedInUsersId != null) {
		            Optional<Users> userOptional = us.getUserByUsersId(loggedInUsersId);		            
		            if(userOptional.isPresent()) {
		                Users user = userOptional.get();
		                model.addAttribute("u", user);
		                
		        		List<View_PayAccomodation> payAccList = pas.findAll();
		        		model.addAttribute("payAccList",payAccList);
		                
		                return "/mypage/mypageTravel_Accomodation";
		            }
		        }
		        return "redirect:/header/original_login";
			}
		    
			

		    @GetMapping("/mypage/mypageTravel_Activity")
		    public String list2(Model model,HttpSession session) {
		        String loggedInUsersId = (String) session.getAttribute("loggedInUserId");
		        if(loggedInUsersId != null) {
		            Optional<Users> userOptional = us.getUserByUsersId(loggedInUsersId);		            
		            if(userOptional.isPresent()) {
		                Users user = userOptional.get();
		                model.addAttribute("u", user);
		                
		                List<View_PayActivity> payActList = pActs.findAllByUsersNo(1);
		                model.addAttribute("payActList", payActList);
		                
		                return "/mypage/mypageTravel_Activity";
		            }
		        }
		        return "redirect:/header/original_login";
		    }
}
