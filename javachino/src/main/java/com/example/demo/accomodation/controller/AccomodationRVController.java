package com.example.demo.accomodation.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.accomodation.dto.AccomodationRvDTO;
import com.example.demo.accomodation.service.AccomodationRVService;

@Controller
public class AccomodationRVController {
	@Autowired
	private AccomodationRVService accomodationRVService;

	@GetMapping("/accomodation/confirm")
	@ResponseBody
	public void payOk(
			int usersNo, int accomodationNo, String accomodationRvCheckin, 
			String accomodationRvCheckout, String accomodationRvName, 
			String accomodationRvPhone, String accomodationRvPeople) {

		AccomodationRvDTO ard = new AccomodationRvDTO();
		ard.setAccomodationNo(accomodationNo);
		ard.setAccomodationRvCheckin(LocalDate.parse(accomodationRvCheckin, DateTimeFormatter.ISO_DATE));
		ard.setAccomodationRvCheckout(LocalDate.parse(accomodationRvCheckout, DateTimeFormatter.ISO_DATE));
		ard.setAccomodationRvName(accomodationRvName);
		ard.setAccomodationRvPeople(accomodationRvPeople);
		ard.setUsersNo(usersNo);
		ard.setAccomodationRvPhone(accomodationRvPhone);
		System.out.println(ard);

		accomodationRVService.insertAccomodationRV(ard);

		/*
		 * String confirmUrl = "/accomodation/confirm?imp_uid=" + imp_uid +
		 * "&merchant_uid=" + merchant_uid + "&paid_amount=" + paid_amount +
		 * "&apply_num=" + apply_num + "&accomodationNo=" + accomodationNo + "&usersNo="
		 * + usersNo + "&checkin=" + checkin + "&checkout=" + checkout + "&name=" + name
		 * + "&phone=" + phone + "&people=" + people;
		 */
		// 확인 페이지로 리다이렉션
		// return ResponseEntity.ok(confirmUrl);
		// return "/accomodation/confirm";
	}

	/*
	 * @GetMapping("/accomodation/confirm") public String test123() {
	 * 
	 * return "/accomodation/confirm"; }
	 */

}
