package com.example.demo.community.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.catalina.connector.Response;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.community.repository.CommunityRepository;
import com.example.demo.community.service.MainAccomodationRVService;
import com.example.demo.community.service.MainAccomodationService;
import com.example.demo.community.service.MainActivityRvService;
import com.example.demo.community.service.CommentaryService;
import com.example.demo.community.service.CommunityLikeService;
import com.example.demo.community.service.CommunityService;
import com.example.demo.community.service.MainUsersService;
import com.example.demo.entity.AccomodationRV;
import com.example.demo.entity.ActivityRv;
import com.example.demo.entity.Commentary;
import com.example.demo.entity.Community;
import com.example.demo.entity.Users;
import com.example.demo.entity.View_AccomodationList;
import com.example.demo.entity.View_CommunityList;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import lombok.Setter;

@Controller
@Setter
public class CommunityController {
	public int pageSIZE = 5;
	public int totalRecordAll;
	public int totalPageAll;
	public int totalRecordBlog;
	public int totalPageBlog;
	public int totalRecordBoard;
	public int totalPageBoard;
	
	@Autowired
	private CommunityService cs;
	
	@Autowired
	private MainUsersService us;
	
	@Autowired
	private CommentaryService commentaryService;
	
	@Autowired
	private CommunityLikeService communityLikeService;
	
	@Autowired
	private MainActivityRvService activityRvService;
	
	@Autowired
	private MainAccomodationRVService accomodationRVService;
	
	@Autowired
	private MainAccomodationService accomodationService;
	
	
	//뷰로 커뮤니티 화면 보여주기!
	@RequestMapping(value = {"/main/board/{pageNUM}/{cname}/{keyword}",
			"/main/board/{cname}/",
			"/main/board/{pageNUM}"})
	public String AllList(Model model, @PathVariable("pageNUM") int pageNUM,
            @RequestParam(defaultValue = "latest") String sortBy,
            @PathVariable(required = false) String keyword,
            @PathVariable(required = false) String cname) {
		//전체 페이징
		if (keyword != null && !keyword.isEmpty()) {
	        totalRecordAll = cs.getTotalAllRecordByKeyword(cname,keyword);
	    } else {
	        totalRecordAll = cs.getTotalRecordAll();
	    }

	    totalPageAll = (int) Math.ceil(totalRecordAll / (double) pageSIZE);
	    
	    //블로그 페이징
	    if (keyword != null && !keyword.isEmpty()) {
	        totalRecordBlog = cs.getTotalBlogRecordByKeyword(cname,keyword);
	    } else {
	    	totalRecordBlog = cs.getTotalRecordBlog();
	    }

	    totalPageBlog = (int) Math.ceil(totalRecordBlog / (double) pageSIZE);
	    
	    //게시판 페이징
	    if (keyword != null && !keyword.isEmpty()) {
	        totalRecordBoard = cs.getTotalBoardRecordByKeyword(cname,keyword);
	    } else {
	    	totalRecordBoard = cs.getTotalRecordBoard();
	    }

	    totalPageBoard = (int) Math.ceil(totalRecordBoard / (double) pageSIZE);
	    

	    int start = (pageNUM - 1) * pageSIZE + 1;
	    int end = start + pageSIZE - 1;

	    //전체
	    List<View_CommunityList> allList;

	    if (keyword != null && !keyword.isEmpty()) {
	    	allList = cs.findAllBy(start, end, cname, keyword, sortBy);
	    } else {
	    	allList = cs.findAllBy(start, end, cname, null, sortBy);
	    }
	    
	    //블로그
	    List<View_CommunityList> blogList;
	    
	    if (keyword != null && !keyword.isEmpty()) {
	    	blogList = cs.findBlogBy(start, end, cname, keyword, sortBy);
	    } else {
	    	blogList = cs.findBlogBy(start, end, cname, null, sortBy);
	    }
	    
	  //게시판
	    List<View_CommunityList> boardList;
	    
	    if (keyword != null && !keyword.isEmpty()) {
	    	boardList = cs.findBoardBy(start, end, cname, keyword, sortBy);
	    } else {
	    	boardList = cs.findBoardBy(start, end, cname, null, sortBy);
	    }
	    
	    
	    //best
	    List<Community> bestList = cs.selectThird();
	    
	    for(Community community : bestList ) {
	    	if(community.getAccomodationRv()!=null) {
	    	View_AccomodationList view_AccomodationList =accomodationService.findByAccomodationNo(community.getAccomodationRv().getAccomodationRVNo());
	    	model.addAttribute("view",view_AccomodationList);
	    	}
	    	
	    }
	    
	    model.addAttribute("best",cs.selectThird());
	    
	    
	    //전체
	    model.addAttribute("allList", allList);
	    model.addAttribute("totalPageAll", totalPageAll);
	    //블로그
	    model.addAttribute("blogList", blogList);
	    model.addAttribute("totalPageBlog", totalPageBlog);
	    //게시판
	    model.addAttribute("boardList", boardList);
	    model.addAttribute("totalPageBoard", totalPageBoard);
	    
	  //로그인유저임의로넣기
	  		Users user = us.getUserById("user1@example.com");
	  		model.addAttribute("u",user);
	  		System.out.println("usersNo"+user.getUsersNo());

	    return "/main/board2";
	}
	
	/*
	@RequestMapping(value = {"/main/board/{pageNUM}/{cname}/{keyword}",
			"/main/board/{cname}/",
			"/main/board/{pageNUM}"})
	public String list(Model model, @PathVariable("pageNUM") int pageNUM,
	                   @RequestParam(defaultValue = "latest") String sortBy,
	                   @PathVariable(required = false) String keyword,
	                   @PathVariable(required = false) String cname) {

	    if (keyword != null && !keyword.isEmpty()) {
	        totalRecord = cs.getTotalRecordByKeyword(cname,keyword);
	    } else {
	        totalRecord = cs.getTotalRecord();
	    }

	    totalPage = (int) Math.ceil(totalRecord / (double) pageSIZE);

	    int start = (pageNUM - 1) * pageSIZE + 1;
	    int end = start + pageSIZE - 1;

	    List<Community> resultList;

	    if (keyword != null && !keyword.isEmpty()) {
	        resultList = cs.findBy(start, end, cname, keyword, sortBy);
	    } else {
	        resultList = cs.findBy(start, end, cname, null, sortBy);
	    }
	    

	    List<Community> bestList = cs.selectThird();
	    
	    for(Community community : bestList ) {
	    	if(community.getAccomodationRv()!=null) {
	    	View_AccomodationList view_AccomodationList =accomodationService.findByAccomodationNo(community.getAccomodationRv().getAccomodationRVNo());
	    	model.addAttribute("view",view_AccomodationList);
	    	}
	    	
	    }
	    
	    model.addAttribute("best",cs.selectThird());
	    
	    
	    
	    model.addAttribute("list", resultList);
	    model.addAttribute("totalPage", totalPage);
	  //로그인유저임의로넣기
	  		Users user = us.getUserById("user1@example.com");
	  		model.addAttribute("u",user);
	  		System.out.println("usersNo"+user.getUsersNo());

	    return "/main/board2";
	}

*/
	
	//
	
//	
//	//페이징된리스트
//	@RequestMapping("/main/board/{pageNUM}")
//	public String list(Model model, @PathVariable("pageNUM") int pageNUM,@RequestParam(defaultValue = "latest") String sortBy) {
//		totalRecord = cs.getTotalRecord();
//		totalPage = (int)Math.ceil(totalRecord/(double)pageSIZE);
//		System.out.println("전체레코드수:"+totalRecord);
//		System.out.println("전체페이지수:"+totalPage);
//		System.out.println("현재페이지:"+pageNUM);
//		int start = (pageNUM-1)*pageSIZE+1;
//		int end = start+pageSIZE-1;
//		System.out.println("start:"+start);
//		System.out.println("end:"+end);
//		//최신순이면
//		if(sortBy.equals("recommendation")) {
//		model.addAttribute("list", cs.selectAllByOrderByCommunityNo(start, end));
//			System.out.println("findAllNo!!!!!!!!!!!!!!!!!!!!!"+cs.selectAllByOrderByCommunityNo(start, end));
//		}else {
//			model.addAttribute("list", cs.selectAllByOrderByCommunityDate(start, end));
//			System.out.println("findAllDate!!!!!!!!!!!!!!!!!!!!!"+cs.selectAllByOrderByCommunityDate(start, end));
//		}
//		model.addAttribute("totalPage", totalPage);
//		System.out.println("컨트롤러 솔트바이"+sortBy);
//		return "/main/board2";
//	}
	
//	/
//	@RequestMapping("/main/board")
//	public String board(Model model, @RequestParam(defaultValue = "latest") String sortBy) {
//		if(sortBy.equals("recommendation")) {
//			model.addAttribute("list",cs.findAllByOrderByCommunityNoDesc());
//			System.out.println("findAllNo!!!!!!!!!!!!!!!!!!!!!"+cs.findAllByOrderByCommunityNoDesc());
//		}else {
//			model.addAttribute("list",cs.findAllByOrderByCommunityDateDesc());
//			System.out.println("findAllDate!!!!!!!!!!!!!!!!!!!!!"+cs.findAllByOrderByCommunityDateDesc());
//		}
//		System.out.println("컨트롤러 솔트바이"+sortBy);
//		return "main/board";
//	}
	
	@RequestMapping("/main/boardwriting/{usersNo}")
	public String list2(Model model, @PathVariable("usersNo") Integer usersNo) {
		System.out.println("userno작성"+usersNo);
		System.out.println("dddd"+activityRvService.findAllByUsersNo(usersNo));
		model.addAttribute("acRv",activityRvService.findAllByUsersNo(usersNo));
		model.addAttribute("accomRv",accomodationRVService.findAllByUsersNo(usersNo));
		System.out.println("accomo"+accomodationRVService.findAllByUsersNo(usersNo));
		return "/main/boardwriting";
	}
	
	@GetMapping("/main/communityDetail")
	public void list3() {
		
	}
	//게시글 상세조회
	@GetMapping("/main/communityDetail2")
	public void communityDetail(Model model, @RequestParam("communityNo") Integer communityNo) {
		System.out.println("컨트롤러 디테일 조회하는거 왔다!!!");
		cs.updateHit(communityNo);
		
		Community community = cs.findById(communityNo);
		model.addAttribute("c",community);
		//로그인유저임의로넣기
		Users user = us.getUserById("user1@example.com");
		model.addAttribute("u",user);
		//좋아요여부
		model.addAttribute("like",communityLikeService.countByUsersNoCommunityNo(user.getUsersNo(), communityNo));
		
		//좋아요 개수
		model.addAttribute("likeCount",communityLikeService.countByCommunityNo(communityNo));
		
		model.addAttribute("list",commentaryService.findAllByCommunityNo(communityNo));
		
		if(community.getAccomodationRv()!=null) {
			System.out.println("숙소예약추가한게시물!");
	    	View_AccomodationList view_AccomodationList =accomodationService.findByAccomodationNo(communityNo);
	    	model.addAttribute("view",view_AccomodationList);
		}
		
	}
//
//	  @RequestMapping(value="uploadSummernoteImageFile" , method = RequestMethod.POST)
//			public @ResponseBody JsonObject SummerNoteImageFile(@RequestParam("file") MultipartFile file) {
//				JsonObject jsonObject = cs.SummerNoteImageFile(file);
//				 System.out.println(jsonObject);
//				return jsonObject;
//			}
	  
	  @PostMapping("/save-content")
	    public String saveContent(Community c, @RequestParam("activityRvNo") Integer activityRvNo, @RequestParam("accomodationRvNo") Integer accomodationRvNo) {
		  System.out.println("인설트 컨트롤러에서 activityNo"+activityRvNo);
		  System.out.println("인설트 컨트롤러에서 accomodationRvNo"+accomodationRvNo);
		  Users user = us.getUserById("user1@example.com");
		  System.out.println("users+"+user);
		  c.setCommunityNo(cs.getNextNo());
		  c.setUsers(user);
		  
		  
		  if(activityRvNo== 0 && accomodationRvNo != null && accomodationRvNo != 0) {
			//System.out.println("accom"+c.getAccomodationRv().getAccomodationRVNo());
			  System.out.println("숙소예약추가함");
			  AccomodationRV accomodationRV = accomodationRVService.findByAccomodationRVNo(accomodationRvNo);
			  c.setAccomodationRv(accomodationRV);
			  cs.insertWithAccom(c);
		  }else if(activityRvNo != null && activityRvNo != 0 && accomodationRvNo == 0){
			//System.out.println("activity"+c.getActivityRv().getActivityRvNo());
			  System.out.println("엑티 예약 추가함");
			  ActivityRv activityRv = activityRvService.findByActivityRvNo(activityRvNo);
			  c.setActivityRv(activityRv);
			  cs.insertWithActivity(c);
		  }else{
			  System.out.println("예약 추가 안함");
			  c.setAccomodationRv(null);
			  c.setActivityRv(null);
			  cs.saveCommunity(c);
		  }
	        return "redirect:/main/board/1"; // 저장 후 폼 페이지로 리다이렉트
	    }
	  
	  //게시글 수정화면 로드
	  @GetMapping("/main/updateCommunity")
	  public void updateCommunityForm(Model model,@RequestParam("communityNo") Integer communityNo) {
		  System.out.println("수정화면 로드하는 컨트롤러 왔다~~");
		  model.addAttribute("c",cs.findById(communityNo));
	  }
	  
	//게시글 수정
	  @PostMapping("/updateCommunity")
	  public String updateCommunity(Community c) {
		  System.out.println("수정하는 컨트롤러 왔다~~");
		  cs.updateCommunity(c);
		  return "redirect:/main/communityDetail2?communityNo=" + c.getCommunityNo();
	  }
	  
	  //게시글 삭제
	  @GetMapping("/main/deleteCommunity")
	  public String deleteCommunity(@RequestParam("communityNo") Integer communityNo) {
		  System.out.println("삭제하는 컨트ㅗㄹ러 왔다");
		  cs.deleteById(communityNo);
		  return "redirect:/main/board/1";
	  }
	  
	//게시글 작성 시 사진이 있으면 폴더에 저장
	   @PostMapping(value="/uploadSummernoteImageFile")
	   @ResponseBody
	   public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {
	      JsonObject jsonObject = new JsonObject();
	      
	      String fileRoot = "C:\\images\\community\\";   //저장될 외부 파일 경로
	      String originalFileName = multipartFile.getOriginalFilename();   //오리지날 파일명
	      String extension = originalFileName.substring(originalFileName.lastIndexOf("."));   //파일 확장자
	            
	      String savedFileName = UUID.randomUUID() + extension;   //저장될 파일 명
	      File targetFile = new File(fileRoot + savedFileName);   
	      System.out.println(targetFile);
	      HashMap<String, String> jsonResponse = new HashMap<>();
	      
	      try {
	         InputStream fileStream = multipartFile.getInputStream();
	         FileUtils.copyInputStreamToFile(fileStream, targetFile);   //파일 저장
	         jsonResponse.put("url", "/images/community/" + savedFileName);
	         jsonResponse.put("responseCode", "success");

	      } catch (IOException e) {
	         FileUtils.deleteQuietly(targetFile);   //저장된 파일 삭제
	         jsonResponse.put("responseCode", "error");
	         e.printStackTrace();
	      }
	       // Gson 객체를 사용하여 HashMap을 JSON 문자열로 변환
	       Gson gson = new Gson();
	       String jsonString = gson.toJson(jsonResponse);
	       
	      
	      return jsonString;
	   }
}
