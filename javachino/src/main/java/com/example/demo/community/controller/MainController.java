package com.example.demo.community.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.example.demo.entity.Accomodation;
import com.example.demo.entity.Activity;
import com.example.demo.entity.View_AccomodationList;

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
		List<Activity> allActivities = activityService.findAllRownum();
		
		List<Activity> activityListA = new ArrayList<>();
        List<Activity> activityListB = new ArrayList<>();
        List<Activity> activityListC = new ArrayList<>();
        List<Activity> activityListD = new ArrayList<>();
		
        int index = 0;
        for (Activity activity : allActivities) {
            switch (index % 4) {
                case 0:
                    activityListA.add(activity);
                    break;
                case 1:
                    activityListB.add(activity);
                    break;
                case 2:
                    activityListC.add(activity);
                    break;
                case 3:
                    activityListD.add(activity);
                    break;
            }
            index++;
        }
        model.addAttribute("activityListA", activityListA);
        model.addAttribute("activityListB", activityListB);
        model.addAttribute("activityListC", activityListC);
        model.addAttribute("activityListD", activityListD);
        
        model.addAttribute("accomodationList",accomodationService.findByAccomodationNo());
        
		model.addAttribute("activityList2",activityService.findBy());
		//model.addAttribute("accomodationList",accomodationService.findAllRownum());
		model.addAttribute("community",communityService.selectFirst());
		return "/main/mainpage";
	}
	
		
	@PostMapping("/searching")
	public String serching(Model model, @RequestParam String keyword, @RequestParam(defaultValue = "accomodation") String category) {
		if(category.equals("activity")) {
			model.addAttribute("list",activityService.findByActivityAddr(keyword));
			model.addAttribute("category","activity");
		}else {
			List<Accomodation> accomList = accomodationService.findByAccomodationAddr(keyword);
			for(Accomodation accomodation : accomList) {
				View_AccomodationList view_AccomodationList = accomodationService.findByAccomodationNo(accomodation.getAccomodationNo());
				model.addAttribute("view",view_AccomodationList);
			}
			model.addAttribute("list",accomodationService.findByAccomodationAddr(keyword));
			model.addAttribute("category","accomodation");
		}
		model.addAttribute("keyword",keyword);
		return "/main/searching";
	}
	
}
