package com.example.demo.mypage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.db.DBManager;
import com.example.demo.entity.Users;
import com.example.demo.mypage.service.AccomodationReviewService2;
import com.example.demo.mypage.service.ActivityReviewService2;
import com.example.demo.mypage.service.MyUsersService;
import com.example.demo.mypage.service.PayService;
import com.example.demo.vo.AccomodationLikeVO;
import com.example.demo.vo.AccomodationPayVO;
import com.example.demo.vo.ActivityLikeVO;
import com.example.demo.vo.ActivityPayVO;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
@Setter
public class MypageController {

		@Autowired
		private MyUsersService us;
		
		@Autowired
		private PayService ps;
		
		@Autowired
		private AccomodationReviewService2 ars;
		
		@Autowired
		private ActivityReviewService2 actrs;

//mypageProfile controller

			@GetMapping("/mypage/mypageProfile")
		    public String mypageProfile(Model model, HttpSession session) {
		        Users loggedInUser = (Users) session.getAttribute("loggedInUser");
		        if (loggedInUser != null) {
		            model.addAttribute("u", loggedInUser);
		            return "mypage/mypageProfile";
		        }
		        return "redirect:/header/original_login"; 
		    }
		    
			@PostMapping("/mypage/mypageProfile/update")
			public String updateUserProfile(@ModelAttribute Users updatedUser, @RequestParam("profileImage") MultipartFile profileImage, HttpSession session) {
			    String usersId = (String) session.getAttribute("loggedInUserId"); 
			    System.out.println("userUpdateController 전");
			    us.updateUserProfile(usersId, updatedUser, profileImage);
			    System.out.println("userUpdateController 후");
			    return "redirect:/mypage/mypageProfile"; 
			}
		    
		    @GetMapping("/mypage/mypageProfile/delete")
		    public String deleteProfile(HttpSession session) {
		        String loggedInUsersId = (String) session.getAttribute("loggedInUserId");
		        if (loggedInUsersId != null) {
		            us.deleteUserProfile(loggedInUsersId);
		        }
		        System.out.println("회원탈퇴됨");
		        return "redirect:/header/original_login"; 
		    }
		    
		    
//MypageWishlist Controller
		    
		    @GetMapping("/mypage/mypageWishlist_Accomodation")
		    public String mypageWishlistAcc(Model model, HttpSession session) {
		        Users loggedInUser = (Users) session.getAttribute("loggedInUser");

		        if (loggedInUser != null) {
		            int usersNo = loggedInUser.getUsersNo();
		            System.out.println("사용자번호: " + usersNo);

		            List<AccomodationLikeVO> list = DBManager.selectAccomodationLike(usersNo);
		            model.addAttribute("likeAccList", list);
		            model.addAttribute("u", loggedInUser);

		            return "/mypage/mypageWishlist_Accomodation";
		        }

		        return "redirect:/header/original_login";
		    }

		    @GetMapping("/mypage/mypageWishlist_Activity")
		    public String mypageWishlistAct(Model model, HttpSession session) {
		        Users loggedInUser = (Users) session.getAttribute("loggedInUser");

		        if (loggedInUser != null) {
		            int usersNo = loggedInUser.getUsersNo();
		            System.out.println("사용자번호: " + usersNo);

		            List<ActivityLikeVO> list = DBManager.selectActivityLike(usersNo);
		            model.addAttribute("likeActList", list);
		            model.addAttribute("u", loggedInUser);

		            return "/mypage/mypageWishlist_Activity";
		        }

		        return "redirect:/header/original_login";
		    }

			
			
			
//MypageTravel Controller
		    
		    @GetMapping("/mypage/mypageTravel_Accomodation")
		    public String mypageTravelAccomodation(Model model, HttpSession session) {
		        Users loggedInUser = (Users) session.getAttribute("loggedInUser");

		        if (loggedInUser != null) {
		            int usersNo = loggedInUser.getUsersNo();
		            System.out.println("사용자번호: " + usersNo);

		            List<AccomodationPayVO> list = DBManager.selectAccomodationPay(usersNo);
		            model.addAttribute("payAccList", list);
		            model.addAttribute("u", loggedInUser);

		            return "/mypage/mypageTravel_Accomodation";
		        }
		        return "redirect:/header/original_login";
		    }
		    
		    @GetMapping("/mypage/mypageTravel_Activity")
		    public String mypageTravelActivity(Model model, HttpSession session) {
		        Users loggedInUser = (Users) session.getAttribute("loggedInUser");

		        if (loggedInUser != null) {
		            int usersNo = loggedInUser.getUsersNo();
		            System.out.println("사용자번호: " + usersNo);

		            List<ActivityPayVO> list = DBManager.selectActivityPay(usersNo);
		            model.addAttribute("payActList", list);
		            model.addAttribute("u", loggedInUser);

		            return "/mypage/mypageTravel_Activity";
		        }
		        return "redirect:/header/original_login";
		    }

		    @PostMapping("/pay/delete/activity/{payNo}")
		    public ModelAndView deletePayActivity(@PathVariable int payNo, HttpSession session) {
		        Users loggedInUser = (Users) session.getAttribute("loggedInUser");

		        if (loggedInUser != null) {
		            boolean isDeleted = ps.deleteByPayNo1(payNo);

		            if (isDeleted) {
		                System.out.println("Deleted payNo: " + payNo);
		                ModelAndView mav = new ModelAndView("redirect:/mypage/mypageTravel_Activity");
		                mav.addObject("deleted", true);
		                return mav;
		            }
		        }
		        return new ModelAndView("redirect:/mypage/mypageTravel_Activity");
		    }

		    @PostMapping("/pay/delete/accomodation/{payNo}")
		    public ModelAndView deletePayAccomodation(@PathVariable int payNo, HttpSession session) {
		        Users loggedInUser = (Users) session.getAttribute("loggedInUser");

		        if (loggedInUser != null) {
		            boolean isDeleted = ps.deleteByPayNo1(payNo);

		            if (isDeleted) {
		                System.out.println("Deleted payNo: " + payNo);
		                ModelAndView mav = new ModelAndView("redirect:/mypage/mypageTravel_Accomodation");
		                mav.addObject("deleted", true);
		                return mav;
		            }
		        }
		        return new ModelAndView("redirect:/mypage/mypageTravel_Accomodation");
		    }
	    
		    @PostMapping("/review/insert/accomodation")
		    public String insertAccomodationReview(HttpSession session, @RequestParam("accomodationNo") Integer accomodationNo, @RequestParam("accomodationReviewStar") double accomodationReviewStar, @RequestParam("usersNo") Integer usersNo, @RequestParam("accomodationReviewContetnt") String accomodationReviewContent) {
		        Users loggedInUser = (Users) session.getAttribute("loggedInUser");
		        System.out.println("accomodation_review controller 전");
		        ars.insert(accomodationNo, accomodationReviewStar, usersNo, accomodationReviewContent);
		        System.out.println("accomodation_review controller 후");
		        return "redirect:/mypage/mypageTravel_Accomodation";
		    }

		    @PostMapping("/review/insert/activity")
		    public String insertActivityReview(HttpSession session, @RequestParam("activityNo") Integer activityNo, @RequestParam("activityReviewStar") double activityReviewStar, @RequestParam("usersNo") Integer usersNo, @RequestParam("activityReviewContent") String activityReviewContent) {
		        Users loggedInUser = (Users) session.getAttribute("loggedInUser");
		        System.out.println("activity_review controller 전");
		        actrs.insert(activityNo, activityReviewStar, usersNo, activityReviewContent);
		        System.out.println("activity_review controller 후");
		        return "redirect:/mypage/mypageTravel_Activity";
		    }
		    
}
