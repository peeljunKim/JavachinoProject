package com.example.demo.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.community.service.CommunityLikeService;

@Controller
public class CommunityLikeController {
	
	@Autowired
	CommunityLikeService communityLikeService;
	
	@PostMapping("/insertCommunityLike")
	public String insert(Model model, @RequestParam("usersNo") Integer usersNo,@RequestParam("communityNo") Integer communityNo) {
		System.out.println("커뮤니티좋아요등록컨트롤러왔음");
		int communityLikeNo = communityLikeService.getNextNo();
		System.out.println("dd"+communityLikeNo);
		communityLikeService.insert(communityLikeNo, usersNo, communityNo);
		System.out.println("인설트 끝났다!");
		return "redirect:/main/communityDetail2?communityNo="+communityNo;
	}
	
	@PostMapping("/deleteCommunityLike")
	public String delete(@RequestParam("usersNo") Integer usersNo,@RequestParam("communityNo") Integer communityNo) {
		System.out.println("커뮤니티좋아요취소컨트롤러왔음");
		communityLikeService.delete(usersNo,communityNo);
		return "redirect:/main/communityDetail2?communityNo="+communityNo;
	}
	
}
