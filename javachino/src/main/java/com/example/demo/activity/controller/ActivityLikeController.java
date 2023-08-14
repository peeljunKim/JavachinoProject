package com.example.demo.activity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.activity.service.ActivityLikeService;

@RestController
@RequestMapping("/api/activityLike")
public class ActivityLikeController {

    @Autowired
    private ActivityLikeService activityLikeService;

    @PostMapping("/like")
    public ResponseEntity<String> likeActivity(@RequestParam int usersNo, @RequestParam int activityNo) {
        activityLikeService.likeActivity(usersNo, activityNo);
        return ResponseEntity.ok("Activity liked successfully.");
    }
}
