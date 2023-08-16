package com.example.demo.admin.controller;

import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.admin.service.AdminUsersService;
import com.example.demo.entity.Users;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

@Controller
@Setter
public class AdminUsersController {
	public int pageSIZE = 13;
	public int totalRecord;
	public int totalPage;
	
	@Autowired
	private AdminUsersService us;
	
	@GetMapping(value={"/admin/users/usersList/{pageNUM}",
			"/admin/users/usersList/{pageNUM}/{cname}/{keyword}"})
	public String list(Model model, 
			@PathVariable("pageNUM") int pageNUM,
			@PathVariable(required = false) String cname,
			@PathVariable(required = false) String keyword) {		
		if (keyword != null && !keyword.isEmpty()) {
	           totalRecord = us.getTotalRecordByKeyword(cname,keyword);
	       } else {
	           totalRecord = us.getTotalRecord();
	       }
		totalPage = (int)Math.ceil(totalRecord/(double)pageSIZE);
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
    public ModelAndView insert(Users u, HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("/images");
		System.out.println("path:"+path);
		String fname = null;
		MultipartFile uploadFile = u.getUploadFile();
		fname = uploadFile.getOriginalFilename();
		if(fname != null && !fname.equals("")) {
			try {
				FileOutputStream fos = new FileOutputStream(path+"/"+fname);				
				FileCopyUtils.copy(uploadFile.getBytes(), fos);
				fos.close();
			}catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
			}
		}else {
			fname = "";
		}
		u.setUsersFname(fname);
		
        us.insert(u);
        return new ModelAndView("redirect:/admin/users/usersList/1");
    }
	//수정
	@PostMapping("/users/update/{usersNo}")
    public ModelAndView update(@PathVariable("usersNo") int usersNo, @RequestParam String usersFname, @RequestParam String usersPhone) {
        us.update(usersNo, usersFname, usersPhone);
        return new ModelAndView("redirect:/admin/users/usersList/1");
    }
	 @GetMapping("/users/update/{usersNo}")
	    public ModelAndView showUpdateForm(Model model, @PathVariable("usersNo") int usersNo) {
	        model.addAttribute("usersNo", usersNo);
	        // Load other necessary data for the form if needed
	        return new ModelAndView("redirect:/admin/users/usersList/1");
	    }
	
	//삭제
	@PostMapping("/users/delete")
	public ModelAndView delete(@RequestParam int usersNo) {
	    us.deleteUsers(usersNo);
	    return new ModelAndView("redirect:/admin/users/usersList/1");
	}

	@GetMapping("/users/delete/{usersNo}")
	public ModelAndView delete(@PathVariable("usersNo") int usersNo, Model model) {
	    model.addAttribute("usersNo", usersNo);
	    return new ModelAndView("redirect:/admin/users/usersList/1");
	}
	
	
	
}
