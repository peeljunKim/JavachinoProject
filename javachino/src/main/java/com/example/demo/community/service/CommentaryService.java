package com.example.demo.community.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.community.repository.CommentaryRepository;
import com.example.demo.entity.Commentary;

@Service
public class CommentaryService {

	@Autowired
	private CommentaryRepository commentaryRepository;
	
	//댓글 다음 번호 조회
	public int getNextNo() {
		return commentaryRepository.getNextNo();
	}
	
	//댓글 작성
	public void insertCommentary(int commentaryNo,String commentaryContent,int communityNo,int usersNo) {
		commentaryRepository.insert(commentaryNo,commentaryContent,communityNo,usersNo);
	}
	
	//커뮤니티 번호에 따른 댓글 조회
	public List<Commentary> findAllByCommunityNo(int communityNo){
		return commentaryRepository.findAllByCommunityNo(communityNo);
	}
	
	//댓글 수정
	public void update(int commentaryNo,String commentaryContent) {
		commentaryRepository.update(commentaryNo, commentaryContent);
	}
	
	//댓글 삭제
	public void deleteById(int commentaryNo) {
		commentaryRepository.deleteById(commentaryNo);
	}
}
