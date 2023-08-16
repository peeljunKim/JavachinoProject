package com.example.demo.accomodation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.accomodation.service.AccomodationRVService;

@Controller
public class AccomodationRVController {
	@Autowired
	private AccomodationRVService accomodationRVService;
	
	@PostMapping("/api/accomodationRV/cardConfirm")
	public ResponseEntity<String> insertAndConfirmAccomodation(
			@RequestParam int usersNo, @RequestParam int accomodationNo,
			@RequestParam String checkin, @RequestParam String checkout,
			@RequestParam String name, @RequestParam String phone,
			@RequestParam String imp_uid, @RequestParam String merchant_uid,
            @RequestParam int paid_amount, @RequestParam String apply_num
			){
		accomodationRVService.insertAccomodationRV(accomodationNo, usersNo, checkin, checkout, name, phone);

        String confirmUrl = "/confirm?imp_uid=" + imp_uid + "&merchant_uid=" + merchant_uid +
                            "&paid_amount=" + paid_amount + "&apply_num=" + apply_num +
                            "&accomodationNo=" + accomodationNo + "&usersNo=" + usersNo;

        // 확인 페이지로 리다이렉션
        return ResponseEntity.ok(confirmUrl);
	}
	
	@GetMapping("/confirm")
    public String payok(@RequestParam String imp_uid, @RequestParam String merchant_uid,
                        @RequestParam int paid_amount, @RequestParam String apply_num,
                        @RequestParam int accomodationNo, @RequestParam int usersNo,
                        Model model) {
        String formattedAmount = formatCurrency(paid_amount);
        model.addAttribute("imp_uid", imp_uid);
        model.addAttribute("merchant_uid", merchant_uid);
        model.addAttribute("formattedAmount", formattedAmount);
        model.addAttribute("apply_num", apply_num);
        model.addAttribute("activityNo", accomodationNo);
        model.addAttribute("usersNo", usersNo);
        return "/accomodation/confirm";
    }

    private String formatCurrency(int amount) {
        return amount + "원";
    }
}
