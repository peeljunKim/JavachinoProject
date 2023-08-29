package com.example.demo.admin.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	private final AdminBusinessService adminBusinessService;

    public AdminBusinessController(AdminBusinessService adminBusinessService) {
        this.adminBusinessService = adminBusinessService;
    }

    @GetMapping("/admin/business/businessList")
    public String businessList(Model model, @PageableDefault(page=0, size=13,  sort = "businessNo", direction = Sort.Direction.DESC) Pageable pageable,
    		  @RequestParam(required=false) String businessName,
    		  @RequestParam(required=false) String businessAddr,
    		  @RequestParam(required=false) String businessPhone,
              @RequestParam(required=false) String businessManager) {
    	Page<Business> list;
        if(businessName != null && !businessName.isEmpty()) {
            list = adminBusinessService.findByBusinessNameContaining(businessName, pageable);
        } else if(businessAddr != null && !businessAddr.isEmpty()) {
            list = adminBusinessService.findByBusinessAddrContaining(businessAddr, pageable);
        } else if(businessPhone != null && !businessPhone.isEmpty()) {
        	list = adminBusinessService.findByBusinessPhoneContaining(businessPhone, pageable);
        } else if(businessManager != null && !businessManager.isEmpty()) {
        	list = adminBusinessService.findByBusinessManagerContaining(businessManager, pageable);
        } else {
            list = adminBusinessService.findAll(pageable);
        }
        int nowPage = list.getPageable().getPageNumber()+1;
        int startPage = Math.max(nowPage -4, 1);
        int endPage = Math.min(nowPage +5, list.getTotalPages());
        
        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        
    	model.addAttribute("businessName", businessName);
    	model.addAttribute("businessAddr", businessAddr);
    	model.addAttribute("businessPhone", businessPhone);
    	model.addAttribute("businessManager", businessManager);
        return "/admin/business/businessList";
    }

    //추가기능
    @PostMapping("/business/insert")
    public ModelAndView insert(@ModelAttribute("business") Business b) {
        adminBusinessService.save(b);
        return new ModelAndView("redirect:/admin/business/businessList");
    }
    //수정기능
    @PostMapping("/business/update/{businessNo}")
    public ModelAndView update(@PathVariable int businessNo,
                                @ModelAttribute("business") Business updatedBusiness) {
        adminBusinessService.update(businessNo, updatedBusiness);
        return new ModelAndView("redirect:/admin/business/businessList");
    }

    //삭제기능
    @PostMapping("/business/delete/{businessNo}")
    public ModelAndView delete(@PathVariable int businessNo) {
    	adminBusinessService.delete(businessNo);
    	 System.out.println("bn"+businessNo);
        return new ModelAndView("redirect:/admin/business/businessList");
    }

}
