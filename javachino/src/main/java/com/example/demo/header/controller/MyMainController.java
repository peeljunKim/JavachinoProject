
package com.example.demo.header.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Users;

@Controller
public class MyMainController {
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/header/header")
	public String header() {
		return "/header/header";
	}
	
	@GetMapping("/header/login")
	public String login() {
		return "/header/login";
	}
	
	@GetMapping("/header/original_login")
	public String original_login() {
		return "/header/original_login";
	}
	
	@GetMapping("/header/original_join")
	public String original_join() {
		return "/header/original_join";
	}

}

