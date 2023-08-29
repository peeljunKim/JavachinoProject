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

import com.example.demo.admin.service.AdminAccomodationService;
import com.example.demo.entity.Accomodation;

import lombok.Setter;

@Controller
@Setter
public class AdminAccomodationController {

    private final AdminAccomodationService adminAccomodationService;

    public AdminAccomodationController(AdminAccomodationService adminAccomodationService) {
        this.adminAccomodationService = adminAccomodationService;
    }

    @GetMapping("/admin/accomodation/accomodationList")
    public String accomodationList(Model model, @PageableDefault(page=0, size=13,  sort = "accomodationNo", direction = Sort.Direction.DESC) Pageable pageable,
    		  @RequestParam(required=false) String accomodationName,
              @RequestParam(required=false) String accomodationAddr) {
    	Page<Accomodation> list;
        if(accomodationName != null && !accomodationName.isEmpty()) {
            list = adminAccomodationService.findByAccomodationNameContaining(accomodationName, pageable);
        } else if(accomodationAddr != null && !accomodationAddr.isEmpty()) {
            list = adminAccomodationService.findByAccomodationAddrContaining(accomodationAddr, pageable);
        } else {
            list = adminAccomodationService.findAll(pageable);
        }
        int nowPage = list.getPageable().getPageNumber()+1;
        int startPage = Math.max(nowPage -4, 1);
        int endPage = Math.min(nowPage +5, list.getTotalPages());
        
        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        
    	model.addAttribute("accomodationName", accomodationName);
    	model.addAttribute("accomodationAddr", accomodationAddr);
        return "/admin/accomodation/accomodationList";
    }
    //추가기능
    @PostMapping("/accomodation/insert")
    public ModelAndView insert(@ModelAttribute("accomodation") Accomodation a) {
    	adminAccomodationService.save(a);
        return new ModelAndView("redirect:/admin/accomodation/accomodationList");
    }
    
    //수정기능
    @PostMapping("/accomodation/update/{accomodationNo}")
    public ModelAndView update(@PathVariable int accomodationNo,
                                @ModelAttribute("accomodation") Accomodation updatedAccomodation) {
    	adminAccomodationService.update(accomodationNo, updatedAccomodation);
        return new ModelAndView("redirect:/admin/accomodation/accomodationList");
    }
    
    //삭제기능(파일은 혹시모르니 삭제 같이 안하는걸로)
    @PostMapping("/accomodation/delete/{accomodationNo}")
    public ModelAndView delete(@PathVariable int accomodationNo) {
    	adminAccomodationService.delete(accomodationNo);
        return new ModelAndView("redirect:/admin/accomodation/accomodationList");
    }

}
