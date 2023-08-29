package com.example.demo.admin.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.admin.service.AdminActivityService;
import com.example.demo.entity.Activity;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

@Controller
@Setter
public class AdminActivityController {

    private final AdminActivityService adminActivityService;

    public AdminActivityController(AdminActivityService adminActivityService) {
        this.adminActivityService = adminActivityService;
    }

    @GetMapping("/admin/activity/activityList")
    public String activityList(Model model, @PageableDefault(page=0, size=13,  sort = "activityNo", direction = Sort.Direction.DESC) Pageable pageable,
    		  @RequestParam(required=false) String activityName,
              @RequestParam(required=false) String activityAddr) {
    	Page<Activity> list;
        if(activityName != null && !activityName.isEmpty()) {
            list = adminActivityService.findByActivityNameContaining(activityName, pageable);
        } else if(activityAddr != null && !activityAddr.isEmpty()) {
            list = adminActivityService.findByactivityAddrContaining(activityAddr, pageable);
        } else {
            list = adminActivityService.findAll(pageable);
        }
        int nowPage = list.getPageable().getPageNumber()+1;
        int startPage = Math.max(nowPage -4, 1);
        int endPage = Math.min(nowPage +5, list.getTotalPages());
        
        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        
    	model.addAttribute("activityName", activityName);
    	model.addAttribute("activityAddr", activityAddr);
        return "/admin/activity/activityList";
    }
    
    //파일이 저장되는경로
    @Value("${file.upload-dir}")
    private String uploadDir;
    //추가기능
    @PostMapping("/activity/insert")
    public ModelAndView insert(@ModelAttribute("activity") Activity a,
                               @RequestParam("uploadFile1") MultipartFile uploadFile1,
                               @RequestParam("uploadFile2") MultipartFile uploadFile2,
                               @RequestParam("uploadFile3") MultipartFile uploadFile3,
                               HttpServletRequest request) {
        //파일경로
    	String path = new File(uploadDir).getAbsolutePath(); 
        System.out.println("path:" + path);

        try {
            String fname1 = generateUniqueFilename(uploadFile1);
            String fname2 = generateUniqueFilename(uploadFile2);
            String fname3 = generateUniqueFilename(uploadFile3);

            saveUploadedFiles(uploadFile1, path, fname1);
            saveUploadedFiles(uploadFile2, path, fname2);
            saveUploadedFiles(uploadFile3, path, fname3);

            a.setActivityFname1(fname1);
            a.setActivityFname2(fname2);
            a.setActivityFname3(fname3);

        } catch (Exception e) {
            System.out.println("예외발생:" + e.getMessage());
        }

        adminActivityService.save(a);

        return new ModelAndView("redirect:/admin/activity/activityList");
    }

    private void saveUploadedFiles(MultipartFile file, String dirPath, String filename) throws IOException {
      if (!file.isEmpty()) {
          File destination = new File(dirPath + File.separator + filename);  
          try (OutputStream fos = new FileOutputStream(destination)) { 
              fos.write(file.getBytes());
          }
      }
    }

    private String generateUniqueFilename(MultipartFile file) throws IOException {
      if (!file.isEmpty()) {
          long n = System.currentTimeMillis();
          String originalName = file.getOriginalFilename();
          int dotIndex = originalName.lastIndexOf(".");
          
          // 확장자가 없는 경우와 있는 경우를 분리하여 처리
          if (dotIndex == -1) { 
              return originalName + "_" + n;
          } else { 
              return originalName.substring(0,dotIndex) + "_" + n +
                     originalName.substring(dotIndex); 
           }
       }
       return null;
    }
    
    //수정기능
    @PostMapping("/activity/update/{activityNo}")
    public ModelAndView update(@PathVariable int activityNo,
                               @ModelAttribute("activity") Activity updatedActivity,
                               @RequestParam("uploadFile1") MultipartFile uploadFile1,
                               @RequestParam("uploadFile2") MultipartFile uploadFile2,
                               @RequestParam("uploadFile3") MultipartFile uploadFile3) {
        String path = new File(uploadDir).getAbsolutePath();
        System.out.println("path:" + path);

        try {
            String fname1 = saveUploadedFiles(uploadFile1, path);
            String fname2 = saveUploadedFiles(uploadFile2, path);
            String fname3 = saveUploadedFiles(uploadFile3, path);

            updatedActivity.setActivityFname1(fname1);
            updatedActivity.setActivityFname2(fname2);
            updatedActivity.setActivityFname3(fname3);

            adminActivityService.update(activityNo, updatedActivity);

         } catch (Exception e) {
             System.out.println("예외발생:" + e.getMessage());
         }

         return new ModelAndView("redirect:/admin/activity/activityList");
    }

    private String saveUploadedFiles(MultipartFile file, String dirPath) throws IOException {
      if (!file.isEmpty()) {
          File uploadDir = new File(dirPath);
          if (!uploadDir.exists()) {
              uploadDir.mkdirs();
          }

          // 원본 파일명 대신 고유한 새로운 파일명을 생성하여 사용
          String filename = generateUniqueFilename(file); 

          File destination = new File(uploadDir.getAbsolutePath() + File.separator + filename);

          try (OutputStream fos = new FileOutputStream(destination)) { 
              fos.write(file.getBytes());
              return filename;
         }
     }

     return null;
    }
    
    //삭제기능(파일은 혹시모르니 삭제 같이 안하는걸로)
    @PostMapping("/activity/delete/{activityNo}")
    public ModelAndView delete(@PathVariable int activityNo) {
        adminActivityService.delete(activityNo);
        return new ModelAndView("redirect:/admin/activity/activityList");
    }

}
