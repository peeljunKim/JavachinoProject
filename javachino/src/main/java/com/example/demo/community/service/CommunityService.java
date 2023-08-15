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

import com.example.demo.entity.Community;
import com.example.demo.entity.Users;
import com.example.demo.community.repository.CommunityRepository;
import com.google.gson.JsonObject;

import lombok.Setter;

@Service
@Setter
public class CommunityService {
	@Autowired
	private CommunityRepository communityRepository;
	
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
	 
	
	//전체레코드 수를 반환하는 메소드정의
		public int getTotalRecord() {
			return (int)communityRepository.count();
		}
		
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
				case "communityTitle": list=communityRepository.findByCommunityTitleOrderByCommunityNo(start,end,"%"+keyword+"%");break;
				case "communityAddr":list =  communityRepository.findByCommunityAddrOrderByCommunityNo(start,end,"%"+keyword+"%");break;
				}
			}
		}else {
			if(sortBy.equals("latest")) {
				list = communityRepository.selectAllByOrderByCommunityDate(start, end);
			}else {
				list = communityRepository.selectAllByOrderByCommunityNo(start, end);
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
			return communityRepository.selectAllByOrderByCommunityNo(start, end);
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
	
}