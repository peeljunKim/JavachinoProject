package com.example.demo.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminMainController {
	
	@GetMapping("/admin")
	public String index() {
		return "admin/index";
	}
	@GetMapping("admin/sidebar")
	public String sidebar() {
		return "admin/sidebar";
	}

	@GetMapping("admin/hello")
	public String hello() {
		return "admin/hello";
	}
	@GetMapping("admin/addActivity")
	public String addActivity() {
		return "admin/addActivity";
	}
	@GetMapping("admin/addAccomodation")
	public String addAccomodation() {
		return "admin/addAccomodation";
	}
	
}
