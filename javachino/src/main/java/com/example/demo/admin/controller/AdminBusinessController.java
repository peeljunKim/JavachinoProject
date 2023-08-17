package com.example.demo.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.admin.service.AdminBusinessService;
import com.example.demo.entity.Business;

import lombok.Setter;

@Controller
@Setter
public class AdminBusinessController {
	public int pageSIZE = 13;
	public int totalRecord;
	public int totalPage;
	
	@Autowired
	private AdminBusinessService bs;
	
	@GetMapping(value={"/admin/business/businessList/{pageNUM}",
					   "/admin/business/businessList/{pageNUM}/{cname}/{keyword}"})
	public String list(Model model, 
			@PathVariable("pageNUM") int pageNUM,
			@PathVariable(required = false) String cname,
			@PathVariable(required = false) String keyword) {		
		if(keyword != null && !keyword.equals("")){
	           totalRecord = bs.getTotalRecordByKeyword(cname,keyword);
	       } else {
	           totalRecord = bs.getTotalRecord();
	       }
		totalPage = (int)Math.ceil(totalRecord/(double)pageSIZE);
		System.out.println("전체레코드수 : "+totalRecord);
		System.out.println("전체페이지수 : "+totalPage);
		System.out.println("현재페이지수 : "+pageNUM);
		int start = (pageNUM-1)*pageSIZE+1;
		int end=start+pageSIZE-1;
		System.out.println("start:"+start);
		System.out.println("end:"+end);
		model.addAttribute("list", bs.findAll(start, end, cname, keyword));
		System.out.println("fb"+ bs.findAll(start, end, cname, keyword));
		model.addAttribute("totalPage", totalPage);
		return "/admin/business/businessList";
	}
	//추가
	@PostMapping("/business/insert")
    public ModelAndView insert(Business b) {
		bs.insert(b);
        return new ModelAndView("redirect:/admin/business/businessList/1");
    }
	//수정
	@PostMapping("/business/update/{businessNo}")
    public ModelAndView update(@PathVariable("businessNo") int businessNo, @RequestParam String businessName, @RequestParam String businessAddr, @RequestParam String businessPhone, @RequestParam String businessManager) {
		bs.update(businessNo, businessName, businessAddr, businessPhone, businessManager);
        return new ModelAndView("redirect:/admin/business/businessList/1");
    }
	 @GetMapping("/business/update/{businessNo}")
	    public ModelAndView showUpdateForm(Model model, @PathVariable("businessNo") int businessNo) {
	        model.addAttribute("businessNo", businessNo);
	        // Load other necessary data for the form if needed
	        return new ModelAndView("redirect:/admin/business/businessList/1");
	    }
	
	//삭제
	@PostMapping("/business/delete")
	public ModelAndView delete(@RequestParam int businessNo) {
		bs.deleteBusiness(businessNo);
	    return new ModelAndView("redirect:/admin/business/businessList/1");
	}

	@GetMapping("/business/delete/{businessNo}")
	public ModelAndView delete(@PathVariable("businessNo") int businessNo, Model model) {
	    model.addAttribute("businessNo", businessNo);
	    return new ModelAndView("redirect:/admin/business/businessList/1");
	}
}
