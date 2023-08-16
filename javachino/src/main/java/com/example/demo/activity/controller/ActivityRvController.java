package com.example.demo.activity.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.activity.service.ActivityRvService;

@Controller
public class ActivityRvController {

    @Autowired
    private ActivityRvService activityRvService;

    @PostMapping("/api/activityRv/cardConfirm")
    public ResponseEntity<String> reserveAndConfirmActivity(@RequestParam int usersNo, @RequestParam int activityNo,
                                                             @RequestParam LocalDate activityRvDate, @RequestParam int activityRvPeople,
                                                             @RequestParam String activityRvPhone,
                                                             @RequestParam String imp_uid, @RequestParam String merchant_uid,
                                                             @RequestParam int paid_amount, @RequestParam String apply_num) {
        activityRvService.reserveActivity(usersNo, activityNo, activityRvDate, activityRvPeople, activityRvPhone);

        // 여기에서 결제 확인 작업 수행
        String confirmUrl = "/confirm?imp_uid=" + imp_uid + "&merchant_uid=" + merchant_uid +
                            "&paid_amount=" + paid_amount + "&apply_num=" + apply_num +
                            "&activityNo=" + activityNo + "&usersNo=" + usersNo;

        // 확인 페이지로 리다이렉션
        return ResponseEntity.ok(confirmUrl);
    }

    @GetMapping("/activity/confirm")
    public String payok(@RequestParam String imp_uid, @RequestParam String merchant_uid,
                        @RequestParam int paid_amount, @RequestParam String apply_num,
                        @RequestParam int activityNo, @RequestParam int usersNo,
                        Model model) {
        String formattedAmount = formatCurrency(paid_amount);
        model.addAttribute("imp_uid", imp_uid);
        model.addAttribute("merchant_uid", merchant_uid);
        model.addAttribute("formattedAmount", formattedAmount);
        model.addAttribute("apply_num", apply_num);
        model.addAttribute("activityNo", activityNo);
        model.addAttribute("usersNo", usersNo);
        return "activity/confirm"; // 뷰 페이지 이름 반환
    }

    private String formatCurrency(int amount) {
        return amount + "원";
    }
}