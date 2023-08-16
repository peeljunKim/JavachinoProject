package com.example.demo.admin.controller;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.admin.service.AdminActivityService;
import com.example.demo.admin.service.AdminBusinessService;
import com.example.demo.admin.service.AdminUsersService;
import com.example.demo.entity.Activity;
import com.example.demo.entity.Business;
import com.example.demo.entity.Users;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

@Controller
@Setter
public class AdminActivityController {
	public int pageSIZE = 13;
	public int totalRecord;
	public int totalPage;
	
	@Autowired
	private AdminActivityService as;

    @PostMapping("/activity/insert")
    public ModelAndView insert(@ModelAttribute("activity") Activity a,
                               @RequestParam("uploadFile1") MultipartFile uploadFile1,
                               @RequestParam("uploadFile2") MultipartFile uploadFile2,
                               @RequestParam("uploadFile3") MultipartFile uploadFile3,
                               HttpServletRequest request) {
        String path = request.getServletContext().getRealPath("/images");
        System.out.println("path:" + path);
        String fname1 = null;
        String fname2 = null;
        String fname3 = null;

        try {
            fname1 = uploadFile1.getOriginalFilename();
            fname2 = uploadFile2.getOriginalFilename();
            fname3 = uploadFile3.getOriginalFilename();

            File uploadDir = new File(path);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            File file1 = new File(uploadDir.getAbsolutePath() + File.separator + fname1);
            File file2 = new File(uploadDir.getAbsolutePath() + File.separator + fname2);
            File file3 = new File(uploadDir.getAbsolutePath() + File.separator + fname3);

            FileOutputStream fos1 = new FileOutputStream(file1);
            FileOutputStream fos2 = new FileOutputStream(file2);
            FileOutputStream fos3 = new FileOutputStream(file3);

            fos1.write(uploadFile1.getBytes());
            fos2.write(uploadFile2.getBytes());
            fos3.write(uploadFile3.getBytes());

            fos1.close();
            fos2.close();
            fos3.close();
        } catch (Exception e) {
            System.out.println("예외발생:" + e.getMessage());
        }

        a.setActivityFname1(fname1);
        a.setActivityFname2(fname2);
        a.setActivityFname3(fname3);
        as.insert(a);

        return new ModelAndView("redirect:/admin/adminPage");
    }
//	//수정
//	@PostMapping("/users/update/{usersNo}")
//    public ModelAndView update(@PathVariable("usersNo") int usersNo, @RequestParam String usersFname, @RequestParam String usersPhone) {
//		as.update(usersNo, usersFname, usersPhone);
//        return new ModelAndView("redirect:/admin/users/usersList/1");
//    }
//	 @GetMapping("/users/update/{usersNo}")
//	    public ModelAndView showUpdateForm(Model model, @PathVariable("usersNo") int usersNo) {
//	        model.addAttribute("usersNo", usersNo);
//	        // Load other necessary data for the form if needed
//	        return new ModelAndView("redirect:/admin/users/usersList/1");
//	    }
//	
//	//삭제
//	@PostMapping("/users/delete")
//	public ModelAndView delete(@RequestParam int usersNo) {
//		as.deleteUsers(usersNo);
//	    return new ModelAndView("redirect:/admin/users/usersList/1");
//	}
//
//	@GetMapping("/users/delete/{usersNo}")
//	public ModelAndView delete(@PathVariable("usersNo") int usersNo, Model model) {
//	    model.addAttribute("usersNo", usersNo);
//	    return new ModelAndView("redirect:/admin/users/usersList/1");
//	}
	
	
	
}
