package com.example.demo.community.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.community.repository.CommunityRepository;
import com.example.demo.community.repository.View_CommunityListRepository;
import com.example.demo.entity.Community;
import com.example.demo.entity.Users;
import com.example.demo.entity.View_CommunityList;
import com.google.gson.JsonObject;

import lombok.Setter;

@Service
@Setter
public class CommunityService {
	@Autowired
	private CommunityRepository communityRepository;
	@Autowired
	private View_CommunityListRepository communityListRepository;
	
	/* public List<Community> findAll(String sortBy){
		List<Community> communityList;
        
        switch (sortBy) {
            case "recommendation":
                // 추천순으로 정렬 (예시: 추천수 기준으로 역순 정렬)
                communityList = core.findAll(Sort.by(Sort.Direction.DESC, "communityNo"));
                break;
            case "latest":
            default:
                // 최신순으로 정렬 (예시: 등록일 기준으로 역순 정렬)
                communityList = core.findAll(Sort.by(Sort.Direction.DESC, "communityDate"));
                break;
        }

        return communityList;

	} */
	//뷰로 전체 반환
	public List<View_CommunityList> findAll(){
		return communityListRepository.findAll();
	}
	
	//뷰 전체 
	public List<View_CommunityList> findAllBy(int start, int end, String cname, String keyword, String sortBy){
		List<View_CommunityList> list = null;
		if(keyword != null && !keyword.equals("")) {		
			if(sortBy.equals("latest")) {
				switch(cname) {
					case "communityTitle": list=communityListRepository.findByCommunityTitleOrderByCommunityDate(start,end,"%"+keyword+"%");break;
					
					case "communityAddr":list =  communityListRepository.findByCommunityAddrOrderByCommunityDate(start,end,"%"+keyword+"%");break;
				}	
			}else {
				switch(cname) {
				case "communityTitle": list=communityListRepository.findByCommunityTitleOrderByCommunityHit(start,end,"%"+keyword+"%");break;
				case "communityAddr":list =  communityListRepository.findByCommunityAddrOrderByCommunityHit(start,end,"%"+keyword+"%");break;
				}
			}
		//검색이 없을때
		}else {
			if(sortBy.equals("latest")) {
				list = communityListRepository.selectAllByOrderByCommunityDate(start, end);
			}else {
				list = communityListRepository.selectAllByOrderByCommunityHit(start, end);
			}
		}
		return list;
	}
	
	//전체레코드 수를 반환하는 메소드정의
	public int getTotalRecordAll() {
		return (int)communityListRepository.count();
	}
	
	//뷰 전체 검색된 레코드 수 반환
	public int getTotalAllRecordByKeyword(String cname,String keyword) {
		int c;
		if(cname.equals("communityTitle")) {
			c= communityListRepository.countByCommunityTitle("%"+keyword+"%");
		}else{
			c = communityListRepository.countByCommunityAddr("%"+keyword+"%");
		}
		return c;
	}
	
	//뷰 블로그 
	public List<View_CommunityList> findBlogBy(int start, int end, String cname, String keyword, String sortBy){
		List<View_CommunityList> list = null;
		if(keyword != null && !keyword.equals("")) {		
			if(sortBy.equals("latest")) {
				switch(cname) {
					case "communityTitle": list=communityListRepository.findBlogByCommunityTitleOrderByCommunityDate(start,end,"%"+keyword+"%");break;
					
					case "communityAddr":list =  communityListRepository.findBlogByCommunityAddrOrderByCommunityDate(start,end,"%"+keyword+"%");break;
				}	
			}else {
				switch(cname) {
				case "communityTitle": list=communityListRepository.findBlogyCommunityTitleOrderByCommunityHit(start,end,"%"+keyword+"%");break;
				case "communityAddr":list =  communityListRepository.findBlogByCommunityAddrOrderByCommunityHit(start,end,"%"+keyword+"%");break;
				}
			}
		//검색이 없을때
		}else {
			if(sortBy.equals("latest")) {
				list = communityListRepository.selectAllBlogByOrderByCommunityDate(start, end);
			}else {
				list = communityListRepository.selectAllBlogByOrderByCommunityHit(start, end);
			}
		}
		return list;
	}
	
	//전체레코드 수를 반환하는 메소드정의
	public int getTotalRecordBlog() {
		return (int)communityListRepository.countBlog();
	}
	
	//뷰 블로그 검색된 레코드 수 반환
	public int getTotalBlogRecordByKeyword(String cname,String keyword) {
		int c;
		if(cname.equals("communityTitle")) {
			c= communityListRepository.countBlogByCommunityTitle("%"+keyword+"%");
		}else{
			c = communityListRepository.countBlogByCommunityAddr("%"+keyword+"%");
		}
		return c;
	}
	
	//뷰 게시판 
	public List<View_CommunityList> findBoardBy(int start, int end, String cname, String keyword, String sortBy){
		List<View_CommunityList> list = null;
		if(keyword != null && !keyword.equals("")) {		
			if(sortBy.equals("latest")) {
				switch(cname) {
					case "communityTitle": list=communityListRepository.findBoardByCommunityTitleOrderByCommunityDate(start,end,"%"+keyword+"%");break;
					
					case "communityAddr":list =  communityListRepository.findBoardByCommunityAddrOrderByCommunityDate(start,end,"%"+keyword+"%");break;
				}	
			}else {
				switch(cname) {
				case "communityTitle": list=communityListRepository.findBoardyCommunityTitleOrderByCommunityHit(start,end,"%"+keyword+"%");break;
				case "communityAddr":list =  communityListRepository.findBoardByCommunityAddrOrderByCommunityHit(start,end,"%"+keyword+"%");break;
				}
			}
		//검색이 없을때
		}else {
			if(sortBy.equals("latest")) {
				list = communityListRepository.selectAllBoardByOrderByCommunityDate(start, end);
			}else {
				list = communityListRepository.selectAllBoardByOrderByCommunityHit(start, end);
			}
		}
		return list;
	}
	
	//전체레코드 수를 반환하는 메소드정의
	public int getTotalRecordBoard() {
		return (int)communityListRepository.countBoard();
	}
	
	//뷰 게시판 검색된 레코드 수 반환
	public int getTotalBoardRecordByKeyword(String cname,String keyword) {
		int c;
		if(cname.equals("communityTitle")) {
			c= communityListRepository.countBoardByCommunityTitle("%"+keyword+"%");
		}else{
			c = communityListRepository.countBoardByCommunityAddr("%"+keyword+"%");
		}
		return c;
	}
	
	
	
		/*
	
	//검색된 레코드 수 반환
	public int getTotalRecordByKeyword(String cname,String keyword) {
		int c;
		if(cname.equals("communityTitle")) {
			c= communityRepository.countByCommunityTitle("%"+keyword+"%");
		}else{
			c = communityRepository.countByCommunityAddr("%"+keyword+"%");
		}
		return c;
	}
	 */
	
	
		
		/*
	//검색+필터+페이징
	public List<Community> findBy(int start, int end, String cname, String keyword, String sortBy){
		List<Community> list = null;
		if(keyword != null && !keyword.equals("")) {		
			if(sortBy.equals("latest")) {
				switch(cname) {
					case "communityTitle": list=communityRepository.findByCommunityTitleOrderByCommunityDate(start,end,"%"+keyword+"%");break;
					
					case "communityAddr":list =  communityRepository.findByCommunityAddrOrderByCommunityDate(start,end,"%"+keyword+"%");break;
				}	
			}else {
				switch(cname) {
				case "communityTitle": list=communityRepository.findByCommunityTitleOrderByCommunityHit(start,end,"%"+keyword+"%");break;
				case "communityAddr":list =  communityRepository.findByCommunityAddrOrderByCommunityHit(start,end,"%"+keyword+"%");break;
				}
			}
		//검색이 없을때
		}else {
			if(sortBy.equals("latest")) {
				list = communityRepository.selectAllByOrderByCommunityDate(start, end);
			}else {
				list = communityRepository.selectAllByOrderByCommunityHit(start, end);
			}
		}
		return list;
	}
	*/
	
	//블로그일때
	public List<Community> findBlogy(int start, int end, String cname, String keyword, String sortBy){
		List<Community> list = null;
		if(keyword != null && !keyword.equals("")) {		
			if(sortBy.equals("latest")) {
				switch(cname) {
					case "communityTitle": list=communityRepository.findBlogByCommunityTitleOrderByCommunityDate(start,end,"%"+keyword+"%");break;
					
					case "communityAddr":list =  communityRepository.findBlogByCommunityAddrOrderByCommunityDate(start,end,"%"+keyword+"%");break;
				}	
			}else {
				switch(cname) {
				case "communityTitle": list=communityRepository.findBlogByCommunityTitleOrderByCommunityDate(start,end,"%"+keyword+"%");break;
				case "communityAddr":list =  communityRepository.findBlogByCommunityAddrOrderByCommunityHit(start,end,"%"+keyword+"%");break;
				}
			}
		//검색이 없을때
		}else {
			if(sortBy.equals("latest")) {
				list = communityRepository.selectAllBlogByOrderByCommunityDate(start, end);
			}else {
				list = communityRepository.selectAllBlogByOrderByCommunityHit(start, end);
			}
		}
		return list;
	}
	
	
	//페이징..(최신순으로)
	public List<Community> selectAllByOrderByCommunityDate(int start, int end){
		return communityRepository.selectAllByOrderByCommunityDate(start, end);
	}
	
	//페이징..(추천순으로)
		public List<Community> selectAllByOrderByCommunityNo(int start, int end){
			return communityRepository.selectAllByOrderByCommunityHit(start, end);
		}
	
	//최신순으로 정렬
	public List<Community> findAllByOrderByCommunityDateDesc(){
		List<Community> communityList;
		communityList = communityRepository.findAllByOrderByCommunityDateDesc();
		return communityList;
	}
	//추천순으로 정렬(지금은 그냥 no순으로 정렬임)
	public List<Community> findAllByOrderByCommunityNoDesc(){
		return communityRepository.findAllByOrderByCommunityNoDesc();
	}
	
	//썸머노트 인설트..
	public void write(Community community, Users users) {
		communityRepository.save(community);
	}
	
	public int getNextNo() {
		return communityRepository.getNextNo();
	}
	
	public JsonObject SummerNoteImageFile(MultipartFile file) {
		JsonObject jsonObject = new JsonObject();
		String fileRoot = "E:\\javachino2\\javachino_hyun\\src\\main\\resources\\static\\summernoteImg\\";
		String originalFileName = file.getOriginalFilename();
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		
		String saveFileName = UUID.randomUUID()+extension;
			
		File targetFile = new File(fileRoot+saveFileName);
		
		try {
			InputStream fileStream = file.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);
			jsonObject.addProperty("url", "/summernoteImg/"+saveFileName);
			jsonObject.addProperty("responseCode", "success");
		} catch(IOException e) {
			FileUtils.deleteQuietly(targetFile);
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}	
		return jsonObject;
	}
	//커뮤니티 등록
	public void saveCommunity(Community c) {
		communityRepository.insert(c);
		
	}
	
	//커뮤니티 등록(숙소도_)
	public void insertWithAccom(Community c) {
		communityRepository.insertWithAccom(c);
		
	}
	
	//커뮤니티 등록(숙소도_)
		public void insertWithActivity(Community c) {
			communityRepository.insertWithActivity(c);
			
		}
	
	//커뮤니티 상세보기
	public Community findById(int communityNo) {
		Community c = communityRepository.findById(communityNo);
		return c;
	}
	
	//커뮤니티 수정
	public void updateCommunity(Community c) {
		communityRepository.update(c);
	}
	
	//커뮤니티 삭제
	public void deleteById(int communityNo) {
		communityRepository.deleteById(communityNo);
	}
	
	//3위까지
	public List<Community> selectThird(){
		return communityRepository.selectThird();
	}
	
	//1d위
	public Community selectFirst() {
		return communityRepository.selectFirst();
	}
	
	//조회수 업데이트
	public void updateHit(int communityNo) {
		communityRepository.updateHit(communityNo);
	}
	
}