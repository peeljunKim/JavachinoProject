package com.example.demo.admin.controller;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.admin.service.AdminAccomodationService;
import com.example.demo.entity.Accomodation;
import com.example.demo.entity.AccomodationFac;
import com.example.demo.entity.AccomodationFile;
import com.example.demo.entity.AccomodationInfo;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

@Controller
@Setter
public class AdminAccomodationController {
	public int pageSIZE = 13;
	public int totalRecord;
	public int totalPage;
	
	@Autowired
	private AdminAccomodationService as;
	
//	@GetMapping(value={"/admin/users/usersList/{pageNUM}",
//			"/admin/users/usersList/{pageNUM}/{cname}/{keyword}"})
//	public String list(Model model, 
//			@PathVariable("pageNUM") int pageNUM,
//			@PathVariable(required = false) String cname,
//			@PathVariable(required = false) String keyword) {		
//		if (keyword != null && !keyword.isEmpty()) {
//	           totalRecord = as.getTotalRecordByKeyword(cname,keyword);
//	       } else {
//	           totalRecord = as.getTotalRecord();
//	       }
//		totalPage = (int)Math.ceil(totalRecord/(double)pageSIZE);
//		System.out.println("전체레코드수 : "+totalRecord);
//		System.out.println("전체페이지수 : "+totalPage);
//		System.out.println("현재페이지수 : "+pageNUM);
//		int start = (pageNUM-1)*pageSIZE+1;
//		int end=start+pageSIZE-1;
//		System.out.println("start:"+start);
//		System.out.println("end:"+end);
//		model.addAttribute("list", as.findAll(start, end, cname, keyword));
//		model.addAttribute("totalPage", totalPage);
//		return "/admin/users/usersList";
//	}
	//추가
	@PostMapping("accomodation/insert")
    public String insert(@ModelAttribute("accomodation") Accomodation accomodation,
                         @ModelAttribute("accomodationInfo") AccomodationInfo accomodationInfo,
                         @ModelAttribute("accomodationfac") AccomodationFac accomodationfac,
                         @ModelAttribute("accomodationfile") AccomodationFile accomodationfile,
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

        accomodationfile.setAccomodationFileFname1(fname1);
        accomodationfile.setAccomodationFileFname2(fname2);
        accomodationfile.setAccomodationFileFname3(fname3);
        
        as.insertAccomodationAndInfo(accomodation, accomodationInfo,accomodationfac ,accomodationfile);
        return "redirect:/admin/adminPage"; // Redirect to the appropriate page
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
