package com.example.demo.accomodation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.accomodation.service.AccomodationLikeService;

@RestController
@RequestMapping("/api/accomodationLike")
public class AccomodationLikeController {

	@Autowired
	private AccomodationLikeService accomodationLikeService;

	@PostMapping("/like")
	public ResponseEntity<String> likeActivity(@RequestParam int usersNo, @RequestParam int accomodationNo) {
		accomodationLikeService.likeAccomodation(usersNo, accomodationNo);
		return ResponseEntity.ok("Activity liked successfully.");
	}

}
