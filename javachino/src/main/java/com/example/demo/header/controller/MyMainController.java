
package com.example.demo.header.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Users;

@Controller
public class MyMainController {
	
	@GetMapping("/index")
	public String index() {
		return "/index";
	}
	


}

