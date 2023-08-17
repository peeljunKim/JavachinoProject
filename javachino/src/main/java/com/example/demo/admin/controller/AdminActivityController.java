package com.example.demo.admin.controller;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.admin.service.AdminActivityService;
import com.example.demo.entity.Activity;

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
	
	@GetMapping(value={"/admin/activity/activityList/{pageNUM}",
					   "/admin/activity/activityList/{pageNUM}/{cname}/{keyword}"})
	public String list(Model model, 
			@PathVariable("pageNUM") int pageNUM,
			@PathVariable(required = false) String cname,
			@PathVariable(required = false) String keyword) {		
		if(keyword != null && !keyword.equals("")){
		       totalRecord = as.getTotalRecordByKeyword(cname,keyword);
		   } else {
		       totalRecord = as.getTotalRecord();
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
		return "/admin/activity/activityList";
	}

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
	//수정
	@PostMapping("/admin/update/{activityNo}")
    public ModelAndView update(@PathVariable("activityNo") int activityNo, @RequestParam String activityName, @RequestParam String activityAddr,@RequestParam String activityExplanation,@RequestParam String activityPrice,@RequestParam String activityTime,@RequestParam String activityFname1,@RequestParam String activityFname2,@RequestParam String activityFname3) {
		as.update(activityNo, activityName, activityAddr, activityExplanation, activityPrice, activityTime,  activityFname1,activityFname2,activityFname3);
		 return new ModelAndView("redirect:/admin/activity/activityList/1");
    }
	 @GetMapping("/admin/update/{activityNo}")
	    public ModelAndView showUpdateForm(Model model, @PathVariable("activityNo") int activityNo) {
	        model.addAttribute("activityNo", activityNo);
	        // Load other necessary data for the form if needed
	        return new ModelAndView("redirect:/admin/activity/activityList/1");
	    }
	
	//삭제
	@PostMapping("/activity/delete")
	public ModelAndView delete(@RequestParam int activityNo) {
		as.deleteActivity(activityNo);
	    return new ModelAndView("redirect:/admin/activity/activityList/1");
	}

	@GetMapping("/activity/delete/{activityNo}")
	public ModelAndView delete(@PathVariable("activityNo") int activityNo, Model model) {
	    model.addAttribute("activityNo", activityNo);
	    return new ModelAndView("redirect:/admin/activity/activityList/1");
	}
	
	
	
}
