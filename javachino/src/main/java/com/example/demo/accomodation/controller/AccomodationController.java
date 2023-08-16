package com.example.demo.accomodation.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.accomodation.service.AccomodationService;
import com.example.demo.entity.Accomodation;
import com.example.demo.entity.AccomodationFac;
import com.example.demo.entity.AccomodationFile;
import com.example.demo.entity.AccomodationInfo;
import com.example.demo.entity.View_AccomodationList;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Setter;

@Controller
@Setter
public class AccomodationController {
	@Autowired
	AccomodationService as;

	@Autowired
	private EntityManager entityManager;

	@GetMapping("/accomodation")
	public String getAllList(Model model) {
		model.addAttribute("accomodationList", as.findAll());
		return "/accomodation/accomodationMain";
	}
	
	@GetMapping("/accomodation/{accomodationNo}/priceCheck")
	public String getAccomodationPriceDetails(
			@PathVariable ("accomodationNo") int accomodationNo, 
			@RequestParam("dateRange") String dateRange,
	        @RequestParam("people") String people,
			Model model) {
		View_AccomodationList accomodation =as.findAccomodationByNo(accomodationNo);
		Map<String, Object> info = new HashMap<>();
		System.out.println(accomodation);
		String [] arr = dateRange.split(" ~ ");
		String startDate = arr[0];
		String endDate = arr[1];
		info.put("name", accomodation.getAccomodationName());
		info.put("fname", accomodation.getAccomodationFileFname1());
		info.put("price", accomodation.getAccomodationPrice());
		info.put("startDate", startDate);
		info.put("endDate", endDate);
		System.out.println(info);
		model.addAttribute("info", info);
		
		return "/accomodation/priceCheck";
	}
	
	
	@GetMapping("/accomodation/{accomodationNo}")
	public String getAccomodationDetails(@PathVariable int accomodationNo, Model model) {
		AccomodationInfo accomodationInfo = as.findByAccomodationNo(accomodationNo);
		View_AccomodationList otherInformation = as.findAccomodationByNo(accomodationNo);
		AccomodationFile files = as.findByAccomodation_AccomodationNo(accomodationNo);

		String explanation = accomodationInfo.getAccomodationInfoExplanation();
		int maxPerson = accomodationInfo.getAccomodationInfoMaxPersion();
		int minPerson = accomodationInfo.getAccomodationInfoMinPerson();
		int size = accomodationInfo.getAccomodationInfoSize();
		String prieperPerson = accomodationInfo.getAccomodationPriceperPerson();
		
		String addr = otherInformation.getAccomodationAddr();
		String name = otherInformation.getAccomodationName();
		double reviewAvg = otherInformation.getAvgReviewStar();
		int reviewCount = otherInformation.getReviewCount();
		String price = otherInformation.getAccomodationPrice();
		
		
		String fname1 = files.getAccomodationFileFname1();
		String fname2 = files.getAccomodationFileFname2();
		String fname3 = files.getAccomodationFileFname3();

		Map<String, Object> info = new HashMap<>();
		info.put("name", name);
		info.put("explanation", explanation);
		info.put("maxPerson", maxPerson);
		info.put("minPerson", minPerson);
		info.put("size", size);
		info.put("addr", addr);
		info.put("reviewAvg", reviewAvg);
		info.put("reviewCount", reviewCount);
		info.put("fname1", fname1);
		info.put("fname2", fname2);
		info.put("fname3", fname3);
		info.put("pricePerPerson", prieperPerson);
		info.put("price", price);
		info.put("accomodationNo", accomodationNo);

		model.addAttribute("info", info);
		System.out.println(info);
		return "/accomodation/accomodationDetatil";
	}

	@GetMapping("/accomodation/accomodationMain")
	public String getListBySearch(@RequestParam(value = "addr", required = false) String addr,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "person", required = false) String person, Model model) {

		Collection<Integer> accomodationNo = new ArrayList<>();
		List<View_AccomodationList> searchList = new ArrayList<>();
		// 1번 케이스 주소 인원이 검색되면 주소, 인원에 따라 숙소 넘버를 찾고 나중에 예약 날짜로 검색된 숙소 넘버를 뺀다
		// 2번 케이스 주소 인원이 검색 안되면 전체 숙소에서 예약 날짜 검색된 숙소 넘버를 뺀다

		// if랑 else에 같이 들어가서 위로 뺌 가독성은 떨어지는거 같음
		String[] dateRange = date.split(" ~ ");
		String startDate = dateRange[0];
		String endDate = dateRange[1];
		List<Integer> accommodationNosByDateRange = as.findAccommodationNumbersByDateRange(startDate, endDate);

		if (addr.isEmpty() && person.isEmpty()) {
			// 2번 케이스
			List<Integer> allAccomodationNo = as.findAllAccomodationNumbers();
			accomodationNo.addAll(allAccomodationNo);
			accommodationNosByDateRange.forEach(accomRV -> accomodationNo.remove(accomRV));
			searchList = as.findByAccomodationNoIn(accomodationNo);
			System.out.println("accomodationNo" + accomodationNo);
		} else {
			// 1번 케이스 주소만 입력되면 알아서 주소만
			if (!addr.isEmpty()) {
				List<View_AccomodationList> accommodationNosByAddr = as.findByAccomodationAddrContaining(addr);
				accommodationNosByAddr.forEach(accom -> accomodationNo.add(accom.getAccomodationNo()));
				System.out.println("accommodationNosByAddr: " + accommodationNosByAddr);
			}

			if (!person.isEmpty()) {
				int maxPerson = Integer.parseInt(person);
				List<AccomodationInfo> accommodationNosByPerson = as
						.findByAccomodationInfoMaxPersionGreaterThanEqual(maxPerson);
				accommodationNosByPerson
						.forEach(accomInfo -> accomodationNo.add(accomInfo.getAccomodation().getAccomodationNo()));
				System.out.println("accommodationNosByPerson: " + accommodationNosByPerson);
			}

			// distinct로 중복이 제거된 번호를 가지고 와서 전체 번호에서 중복이 제거된 번호를 지우면 중복된 번호만 나온다 단 주소, 인원이
			// 동시에 검색하는 경우에만
			if (!addr.isEmpty() && !person.isEmpty()) {
				Collection<Integer> distinctAccomodationNo = accomodationNo.stream().distinct()
						.collect(Collectors.toList());
				System.out.println("distinctAccomodationNo: " + distinctAccomodationNo);
				for (Integer distinctElement : distinctAccomodationNo) {
					accomodationNo.remove(distinctElement);
				}
			}
			System.out.println("accomodationNo" + accomodationNo);

			accommodationNosByDateRange.forEach(accomRV -> accomodationNo.remove(accomRV));
			System.out.println("accommodationNosByDateRange: " + accommodationNosByDateRange);

			searchList = as.findByAccomodationNoIn(accomodationNo);
		}
		System.out.println("데이터 수"+searchList.size());
		model.addAttribute("accomodationList", searchList);
		System.out.println(as.findAll());
		return "/accomodation/accomodationMain";
	}

	@GetMapping("/filterList")
	public String getListByfilter(@RequestParam(value = "filterData", required = false) String[] filterData,
			Model model) {
		Collection<Integer> accomodationNo = new ArrayList<>();
		List<View_AccomodationList> searchList = new ArrayList<>();

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<AccomodationFac> cq = cb.createQuery(AccomodationFac.class);
		Root<AccomodationFac> root = cq.from(AccomodationFac.class);
		Join<AccomodationFac, Accomodation> join = root.join("accomodation", JoinType.INNER);
		List<Predicate> predicates = new ArrayList<>();

		if (filterData != null && filterData.length > 0) {
			try {
				int accomodationCategory = Integer.parseInt(filterData[0]);
				predicates.add(cb.equal(join.get("accomodationCategory"), accomodationCategory));
			} catch (NumberFormatException e) {
				for (int i = 0; i < filterData.length; i++) {
					predicates.add(cb.equal(root.get(filterData[i]), "y"));
				}
			}

			for (int i = 1; i < filterData.length; i++) {
				predicates.add(cb.equal(root.get(filterData[i]), "y"));
			}
		}

		cq.where(predicates.toArray(new Predicate[0]));

		List<AccomodationFac> resultList = entityManager.createQuery(cq).getResultList();
		resultList.forEach(list -> accomodationNo.add(list.getAccomodation().getAccomodationNo()));
		searchList = as.findByAccomodationNoIn(accomodationNo);
		model.addAttribute("accomodationList", searchList);
		return "accomodationMain";
	}

	// 숙소 디테일 화면에서 데이트 랭스 피커 클릭 시 이미 예약 된 날짜 추출(ajax통신)
	@GetMapping("/getDisabledDates")
	@ResponseBody
	public List<LocalDate> getDisabledDates(int accomodationNo) {
		List<String> range = as.getDateRangesByAccomodationNo(accomodationNo);
		List<LocalDate> startDateList = new ArrayList<>();
		List<LocalDate> endDateList = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

		range.forEach(dateRange -> {
			String[] parts = dateRange.split("~");
			if (parts.length == 2) {
				LocalDate startDate = LocalDate.parse(parts[0].trim(), formatter);
				LocalDate endDate = LocalDate.parse(parts[1].trim(), formatter);
				startDateList.add(startDate);
				endDateList.add(endDate);
			}
		});
		System.out.println("startDateList " + startDateList);
		System.out.println("endDateList " + endDateList);
		List<LocalDate> list = new ArrayList<>();
		for (int i = 0; i < startDateList.size(); i++) {
			LocalDate startDate = startDateList.get(i);
			LocalDate endDate = endDateList.get(i);
			list.addAll(startDate.datesUntil(endDate).collect(Collectors.toList()));
		}
		System.out.println(list);
		return list;
	}

	
	
}
