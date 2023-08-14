package com.example.demo.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminMainController {
	
	@GetMapping("/admin")
	public String index() {
		return "admin/index";
	}
	@GetMapping("/sidebar")
	public String sidebar() {
		return "admin/sidebar";
	}

	@GetMapping("/hello")
	public String hello() {
		return "admin/hello";
	}
	@GetMapping("/dashboard")
	public String dashboard() {
		return "admin/dashboard";
	}
	
}
