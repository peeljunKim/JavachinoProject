package com.example.demo.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.community.service.CommentaryService;

import lombok.Setter;

@Controller
@Setter
public class CommentaryController {

	@Autowired 
	CommentaryService cs;
	
	
	@PostMapping("/insertCommentary")
	public String insertCommentary(@RequestParam("communityNo") Integer communityNo,@RequestParam("commentaryContent") String commentaryContent,@RequestParam("usersNo") Integer usersNo) {
		int commentaryNo=cs.getNextNo();
		cs.insertCommentary(commentaryNo, commentaryContent, communityNo, usersNo);
		return "redirect:/main/communityDetail2?communityNo=" + communityNo;
	}
	
	@PostMapping("/updateCommentary")
	@ResponseBody
	public void update(@RequestParam("commentaryNo") Integer commentaryNo,@RequestParam("commentaryContent") String commentaryContent) {
		System.out.println("댓글 수정 컨트롤러 왔다");
		cs.update(commentaryNo, commentaryContent);
		
	}
	
	//댓글 삭제
	@GetMapping("main/deleteCommentary")
	public String deleteCommentary(@RequestParam("commentaryNo") Integer commentaryNo, @RequestParam("communityNo") Integer communityNo) {
		System.out.println("댓글 삭제 컨트롤렁 ㅘㅆ다");
		cs.deleteById(commentaryNo);
		return "redirect:/main/communityDetail2?communityNo=" + communityNo;
		
	}
}
