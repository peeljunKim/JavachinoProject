package com.example.demo.activity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // Note: Import Controller instead of RestController
import org.springframework.ui.Model; // Import Model
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.activity.dto.ActivityDto;
import com.example.demo.activity.dto.ActivityReviewDto;
import com.example.demo.activity.service.ActivityReviewService;
import com.example.demo.activity.service.ActivityService;
import com.example.demo.entity.ActivityCategory;

import lombok.Setter;

@Controller
@Setter
public class ActivityController {

	@Autowired
	private ActivityService activityService;

	@Autowired
	private ActivityReviewService activityReviewService;

	@GetMapping("/activity")
	public String getPosts(Model model) {
		List<ActivityDto> activityDtoList = activityService.findAll();

		for (ActivityDto activityDto : activityDtoList) {
			double averageRating = activityReviewService.getAverageRatingForActivity(activityDto);
			int reviewCount = activityReviewService.getReviewCountForActivity(activityDto);
			activityDto.setAverageRating(averageRating);
			activityDto.setReviewCount(reviewCount);
		}

		model.addAttribute("activityDtoList", activityDtoList);
		return "/activity/activityMain";
	}

	@GetMapping("/activity/{activityNo}")
	public String getActivityDetails(@PathVariable int activityNo, Model model) {
		ActivityDto activityDto = activityService.getPost(activityNo);
		List<ActivityReviewDto> activityReviewDtoList = activityReviewService.getReviewsByActivityReviewNo(activityNo);

		double averageRating = activityReviewService.getAverageRatingForActivity(activityDto);
		int reviewCount = activityReviewService.getReviewCountForActivity(activityDto);

		model.addAttribute("activityDto", activityDto);
		model.addAttribute("activityReviewDtoList", activityReviewDtoList);
		model.addAttribute("averageRating", averageRating);
		model.addAttribute("reviewCount", reviewCount); // Add reviewCount to the model

		return "/activity/activityDetail";
	}

	@GetMapping("/activity/{activityNo}/priceCheck")
	public String getActivityPriceDetails(@PathVariable int activityNo, Model model) {
		ActivityDto activityDto = activityService.getPost(activityNo);
		model.addAttribute("activityDto", activityDto);
		return "/activity/priceCheck";
	}
	
	@GetMapping("/activity/filter")
	public String filterActivitiesByCategory(@RequestParam String category, Model model) {
	    ActivityCategory activityCategory = null;
	    
	    if ("실내".equals(category)) {
	        activityCategory = ActivityCategory.INDOOR;
	    } else if ("실외".equals(category)) {
	        activityCategory = ActivityCategory.OUTDOOR;
	    }

	    List<ActivityDto> filteredActivityDtoList = activityService.filterActivitiesByCategory(activityCategory);
	    model.addAttribute("activityDtoList", filteredActivityDtoList);
	    return "activityMain :: activityList"; // Return the partial view for the filtered activities
	}
}
