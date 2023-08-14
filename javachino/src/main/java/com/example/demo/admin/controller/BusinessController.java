package com.example.demo.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.admin.service.BusinessService;

import lombok.Setter;

@Controller
@Setter
public class BusinessController {
	public int pageSIZE = 13;
	public int totalRecord;
	public int totalPage;
	
	
	@Autowired
	private BusinessService bs;
	
	@GetMapping("/admin/business/businessList/{pageNUM}")
	public String list(Model model, @PathVariable("pageNUM") int pageNUM) {		
		totalRecord = bs.getTotalRecord();
		
		totalPage = (int)Math.ceil(totalRecord/(double)pageSIZE);
		System.out.println("전체레코드수 : "+totalRecord);
		System.out.println("전체페이지수 : "+totalPage);
		System.out.println("현재페이지수 : "+pageNUM);
		
		int start = (pageNUM-1)*pageSIZE+1;
		int end=start+pageSIZE-1;
		
		System.out.println("start:"+start);
		System.out.println("end:"+end);
		
		model.addAttribute("list", bs.findAll(start, end));
		model.addAttribute("totalPage", totalPage);
		return "/admin/business/businessList";
	}
}
