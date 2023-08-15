package com.example.demo.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CommunityLike;

import jakarta.transaction.Transactional;

@Repository
public interface CommunityLikeRepository extends JpaRepository<CommunityLike, Integer> {

	//좋아요 다음 번호
		@Query("select nvl(max(c.communityLikeNo), 0) + 1 from CommunityLike c")
		public int getNextNo();
		
	//좋아요 등록
		@Modifying
		@Query(value = "insert into community_Like (community_like_no, users_no, community_no) values (:communityLikeNo, :usersNo, :communityNo)", nativeQuery = true)
		@Transactional
		public void insert(
			@Param("communityLikeNo") int communityLikeNo,
		    @Param("usersNo") int usersNo,
		    @Param("communityNo") int communityNo
		);
		
		//좋아요 게시글별 개수
		public int countByCommunityCommunityNo(int communityNo);
		
		//회원이 게시글 좋아요 눌렀나
		public int countByUsersUsersNoAndCommunityCommunityNo(int usersNo, int communityNo);
		
		//좋아요 취소
		@Modifying
	    @Query("DELETE FROM CommunityLike c WHERE c.users.usersNo = :usersNo AND c.community.communityNo = :communityNo")
		@Transactional
	    void deleteByUsersNoAndCommunityNo(@Param("usersNo") int usersNo, @Param("communityNo") int communityNo);

}