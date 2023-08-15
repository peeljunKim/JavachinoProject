package com.example.demo.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.community.service.MainAccomodationService;
import com.example.demo.community.service.MainActivityService;
import com.example.demo.community.service.CommunityService;

import lombok.Setter;

@Controller
@Setter
public class MainController {
	
	@Autowired
	private MainActivityService activityService;
	
	@Autowired
	private MainAccomodationService accomodationService;
	
	@Autowired
	private CommunityService communityService;
	
	@RequestMapping("/main/mainpage")
	public String list(Model model) {
		
		model.addAttribute("activityList",activityService.findAllRownum());
		model.addAttribute("accomodationList",accomodationService.findAllRownum());
		model.addAttribute("community",communityService.selectFirst());
		return "/main/mainpage";
	}
	
		
	@PostMapping("/searching")
	public String serching(Model model, @RequestParam String keyword, @RequestParam(defaultValue = "accomodation") String category) {
		if(category.equals("activity")) {
			model.addAttribute("list",activityService.findByActivityAddr(keyword));
			model.addAttribute("category","activity");
		}else {
			model.addAttribute("list",accomodationService.findByAccomodationAddr(keyword));
			model.addAttribute("category","accomodation");
		}
		model.addAttribute("keyword",keyword);
		return "/main/searching";
	}
	
}
