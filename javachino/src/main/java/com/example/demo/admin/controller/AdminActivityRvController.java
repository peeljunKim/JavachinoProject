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

import com.example.demo.admin.service.AdminActivityRvService;
import com.example.demo.admin.service.AdminActivityService;
import com.example.demo.admin.service.AdminUsersService;
import com.example.demo.entity.Activity;
import com.example.demo.entity.ActivityRv;
import com.example.demo.entity.Users;

import lombok.Setter;

@Controller
@Setter
public class AdminActivityRvController {

    private final AdminActivityRvService adminActivityRvService;
    private final AdminUsersService usersService;  // Assuming you have this service
    private final AdminActivityService activityService;  // Assuming you have this service

    

    public AdminActivityRvController(AdminActivityRvService adminActivityRvService, AdminUsersService usersService,
			AdminActivityService activityService) {
		super();
		this.adminActivityRvService = adminActivityRvService;
		this.usersService = usersService;
		this.activityService = activityService;
	}

	@GetMapping("/admin/activityRv/activityRvList")
    public String activityList(Model model, @PageableDefault(page=0, size=13,  sort = "activityRvNo", direction = Sort.Direction.DESC) Pageable pageable,
    		  @RequestParam(required=false) String usersName,
              @RequestParam(required=false) String activityName) {
    	Page<ActivityRv> list;
    	if(activityName != null && !activityName.isEmpty()) {
    	    list = adminActivityRvService.findByActivity_ActivityNameContaining(activityName, pageable);
    	} else if(usersName != null && !usersName.isEmpty()) {
    	    list = adminActivityRvService.findByUsers_UsersNameContaining(usersName, pageable);
    	} else {
    	    list = adminActivityRvService.findAll(pageable);
    	}
        int nowPage = list.getPageable().getPageNumber()+1;
        int startPage = Math.max(nowPage -4, 1);
        int endPage = Math.min(nowPage +5, list.getTotalPages());
        
        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        
    	model.addAttribute("usersName",  usersService.findAll()); 
    	model.addAttribute("activityName", activityService.findAll());
        return "/admin/activityRv/activityRvList";
    }
    
	//추가기능
	@PostMapping("/activityRv/insert")
	public ModelAndView insert(@ModelAttribute("activityRv") ActivityRv activityRv) {
	    // Find Users and Activities entities by their IDs
	    Users usersEntity = usersService.findById(activityRv.getUsers().getUsersNo())
	        .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + activityRv.getUsers().getUsersNo()));
	    Activity activityEntity = activityService.findById(activityRv.getActivity().getActivityNo())
	        .orElseThrow(() -> new IllegalArgumentException("Invalid activities Id:" + activityRv.getActivity().getActivityNo()));

	    // Set the found entities to the new ActivityRV entity
	    activityRv.setUsers(usersEntity);
	    activityRv.setActivity(activityEntity);

	    adminActivityRvService.save(activityRv);
	    
	     return new ModelAndView("redirect:/admin/activityRv/activityRvList");
	}

	//수정기능
	@PostMapping("/activityRv/update/{activityRvNo}")
	public ModelAndView update(@PathVariable int activityRvNo,
	                            @ModelAttribute("activityRv") ActivityRv updatedActivityRV) {
	    Users userEntity = usersService.findById(updatedActivityRV.getUsers().getUsersNo())
	        .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + updatedActivityRV.getUsers().getUsersNo()));
	    Activity activityEntity = activityService.findById(updatedActivityRV.getActivity().getActivityNo())
	        .orElseThrow(() -> new IllegalArgumentException("Invalid activities Id:" + updatedActivityRV.getActivity().getActivityNo()));

	    // Update the found entities to the ActivityRV entity
	    updatedActivityRV.setUsers(userEntity);
	    updatedActivityRV.setActivity(activityEntity);

	    adminActivityRvService.update(activityRvNo, updatedActivityRV);
	    
	     return new ModelAndView("redirect:/admin/activityRv/activityRvList");
	}

    
    //삭제기능(파일은 혹시모르니 삭제 같이 안하는걸로)
    @PostMapping("/activityRv/delete/{activityRvNo}")
    public ModelAndView delete(@PathVariable int activityRvNo) {
    	adminActivityRvService.delete(activityRvNo);
        return new ModelAndView("redirect:/admin/activityRv/activityRvList");
    }

}
