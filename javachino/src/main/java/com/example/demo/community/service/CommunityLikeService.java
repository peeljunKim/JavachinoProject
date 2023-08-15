package com.example.demo.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.community.repository.CommunityLikeRepository;

@Service
public class CommunityLikeService {
	
	@Autowired
	CommunityLikeRepository communityLikeRepository;
	
	//다음 번호
	public int getNextNo() {
		return communityLikeRepository.getNextNo();
	}
	
	//좋아요 등록
	public void insert(int communityLikeNo,int usersNo,int communityNo) {
		communityLikeRepository.insert(communityLikeNo, usersNo, communityNo);
	}
	
	//좋아요 개수
	public int countByCommunityNo(int communityNo) {
		return communityLikeRepository.countByCommunityCommunityNo(communityNo);
	}
	
	//회원이 게시글 좋아요 눌렀나
	public int countByUsersNoCommunityNo(int usersNo, int communityNo) {
		return communityLikeRepository.countByUsersUsersNoAndCommunityCommunityNo(usersNo, communityNo);
	}
	
	//좋아요취소
	public void delete(int usersNo, int communityNo) {
		communityLikeRepository.deleteByUsersNoAndCommunityNo(usersNo, communityNo);
	}
}
