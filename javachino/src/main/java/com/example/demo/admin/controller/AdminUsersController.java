package com.example.demo.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.admin.service.UsersService;
import com.example.demo.entity.Users;

import lombok.Setter;

@Controller
@Setter
public class AdminUsersController {
	public int pageSIZE = 13;
	public int totalRecord;
	public int totalPage;
	
	@Autowired
	private UsersService us;
	
	@GetMapping(value={"/admin/users/usersList/{pageNUM}",
			"/admin/users/usersList/{pageNUM}/{cname}",
			"/admin/users/usersList/{pageNUM}/{cname}/{keyword}"})
	public String list(Model model, 
			@PathVariable("pageNUM") int pageNUM,
			@PathVariable(required = false) String cname,
			@PathVariable(required = false) String keyword) {		
		totalRecord = us.getTotalRecord();
		if(totalRecord < pageSIZE) {
			totalPage = 1;
		} else {
			totalPage = (int)Math.ceil(totalRecord/(double)pageSIZE);
		}
		System.out.println("전체레코드수 : "+totalRecord);
		System.out.println("전체페이지수 : "+totalPage);
		System.out.println("현재페이지수 : "+pageNUM);
		int start = (pageNUM-1)*pageSIZE+1;
		int end=start+pageSIZE-1;
		System.out.println("start:"+start);
		System.out.println("end:"+end);
		model.addAttribute("list", us.findAll(start, end, cname, keyword));
		model.addAttribute("totalPage", totalPage);
		return "/admin/users/usersList";
	}
	//추가
	@PostMapping("/users/insert")
    public ModelAndView insert(Users u) {
        us.insert(u);
        return new ModelAndView("redirect:/users/usersList/1");
    }
	//수정
	@PostMapping("/users/update/{usersNo}")
	public ModelAndView update(@PathVariable("usersNo") int usersNo, @RequestParam String usersFname, @RequestParam String usersPhone) {
	    us.update(usersNo, usersFname, usersPhone);
	    return new ModelAndView("redirect:/users/usersList/1");
	}
	//삭제
	@PostMapping("/users/delete/{usersNo}")
	public ModelAndView delete(@PathVariable("usersNo") int usersNo) {
	    us.deleteUsers(usersNo);
	    return new ModelAndView("redirect:/users/usersList/1");
	}
	
	
}
