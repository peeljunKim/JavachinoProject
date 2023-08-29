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

import com.example.demo.admin.service.AdminAccomodationRvService;
import com.example.demo.admin.service.AdminAccomodationService;
import com.example.demo.admin.service.AdminUsersService;
import com.example.demo.entity.Accomodation;
import com.example.demo.entity.AccomodationRV;
import com.example.demo.entity.Users;

import lombok.Setter;

@Controller
@Setter
public class AdminAccomodationRvController {

    private final AdminAccomodationRvService adminAccomodationRvService;
    private final AdminUsersService usersService;  // Assuming you have this service
    private final AdminAccomodationService accomodationService;  // Assuming you have this service

	public AdminAccomodationRvController(AdminAccomodationRvService adminAccomodationRvService,
			AdminUsersService usersService, AdminAccomodationService accomodationService) {
		super();
		this.adminAccomodationRvService = adminAccomodationRvService;
		this.usersService = usersService;
		this.accomodationService = accomodationService;
	}

	@GetMapping("/admin/accomodationRv/accomodationRvList")
    public String accomodationRvList(Model model, @PageableDefault(page=0, size=13,  sort = "accomodationRVNo", direction = Sort.Direction.DESC) Pageable pageable,
    		  @RequestParam(required=false) String usersName,
              @RequestParam(required=false) String accomodationName) {
    	Page<AccomodationRV> list;
    	if(accomodationName != null && !accomodationName.isEmpty()) {
    	    list = adminAccomodationRvService.findByAccomodation_AccomodationNameContaining(accomodationName, pageable);
    	} else if(usersName != null && !usersName.isEmpty()) {
    	    list = adminAccomodationRvService.findByUsers_UsersNameContaining(usersName, pageable);
    	} else {
    	    list = adminAccomodationRvService.findAll(pageable);
    	}
        int nowPage = list.getPageable().getPageNumber()+1;
        int startPage = Math.max(nowPage -4, 1);
        int endPage = Math.min(nowPage +5, list.getTotalPages());
        
        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        
        model.addAttribute("usersName", usersService.findAll());
        model.addAttribute("accomodationName", accomodationService.findAll());
        return "/admin/accomodationRv/accomodationRvList";
    }
    
	//추가기능
	@PostMapping("/accomodationRv/insert")
	public ModelAndView insert(@ModelAttribute("accomodationRV") AccomodationRV accomodationRV) {
	    // Find Users and Activities entities by their IDs
	    Users usersEntity = usersService.findById(accomodationRV.getUsers().getUsersNo())
	        .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + accomodationRV.getUsers().getUsersNo()));
	    Accomodation accomodationEntity = accomodationService.findById(accomodationRV.getAccomodation().getAccomodationNo())
	        .orElseThrow(() -> new IllegalArgumentException("Invalid accomodation Id:" + accomodationRV.getAccomodation().getAccomodationNo()));

	    accomodationRV.setUsers(usersEntity);
	    accomodationRV.setAccomodation(accomodationEntity);

	    adminAccomodationRvService.save(accomodationRV);
	    
	     return new ModelAndView("redirect:/admin/accomodationRv/accomodationRvList");
	}

	//수정기능
	@PostMapping("/accomodationRv/update/{accomodationRVNo}")
	public ModelAndView update(@PathVariable int accomodationRVNo,
	                            @ModelAttribute("accomodationRV") AccomodationRV updatedAccomodationRV) {
	    Users userEntity = usersService.findById(updatedAccomodationRV.getUsers().getUsersNo())
	        .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + updatedAccomodationRV.getUsers().getUsersNo()));
	    Accomodation accomodationEntity = accomodationService.findById(updatedAccomodationRV.getAccomodation().getAccomodationNo())
	        .orElseThrow(() -> new IllegalArgumentException("Invalid accomodation Id:" + updatedAccomodationRV.getAccomodation().getAccomodationNo()));

	    updatedAccomodationRV.setUsers(userEntity);
	    updatedAccomodationRV.setAccomodation(accomodationEntity);

	    adminAccomodationRvService.update(accomodationRVNo, updatedAccomodationRV);
	    
	     return new ModelAndView("redirect:/admin/accomodationRv/accomodationRvList");
	}

    
    //삭제기능(파일은 혹시모르니 삭제 같이 안하는걸로)
    @PostMapping("/accomodationRv/delete/{accomodationRVNo}")
    public ModelAndView delete(@PathVariable int accomodationRVNo) {
    	adminAccomodationRvService.delete(accomodationRVNo);
        return new ModelAndView("redirect:/admin/accomodationRv/accomodationRvList");
    }

}
