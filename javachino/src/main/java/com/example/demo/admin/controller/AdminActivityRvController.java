package com.example.demo.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.admin.service.AdminActivityRvService;
import com.example.demo.entity.ActivityRv;

import lombok.Setter;

@Controller
@Setter
public class AdminActivityRvController {
	public int pageSIZE = 13;
	public int totalRecord;
	public int totalPage;
	
	@Autowired
	private AdminActivityRvService as;
	
	@GetMapping(value={"/admin/activity/activityRvList/{pageNUM}",
					   "/admin/activity/activityRvList/{pageNUM}/{cname}/{keyword}"})
	public String list(Model model, 
			@PathVariable("pageNUM") int pageNUM,
			@PathVariable(required = false) String cname,
			@PathVariable(required = false) String keyword) {		
		if(keyword != null && !keyword.equals("")){
		       totalRecord = as.getTotalRecordByKeyword(cname,keyword);
		   } else {
		       totalRecord = as.getActivityRvTotalRecord();
		   }
		totalPage = (int)Math.ceil(totalRecord/(double)pageSIZE);
		System.out.println("전체레코드수 : "+totalRecord);
		System.out.println("전체페이지수 : "+totalPage);
		System.out.println("현재페이지수 : "+pageNUM);
		int start = (pageNUM-1)*pageSIZE+1;
		int end=start+pageSIZE-1;
		System.out.println("start:"+start);
		System.out.println("end:"+end);
		model.addAttribute("list", as.findAll(start, end, cname, keyword));
		System.out.println("fb"+ as.findAll(start, end, cname, keyword));
		model.addAttribute("totalPage", totalPage);
		return "/admin/activity/activityRvList";
	}

    @PostMapping("/activityRv/insert")
    public ModelAndView insert(@ModelAttribute("activityRv") ActivityRv ar) {
        as.insert(ar);
        return new ModelAndView("redirect:/admin/activity/activityRvList/1");
    }

	
	
	
}
