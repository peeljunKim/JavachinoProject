package com.example.demo.community.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Commentary;

import jakarta.transaction.Transactional;

@Repository
public interface CommentaryRepository extends JpaRepository<Commentary, Integer>{
	
	//댓글 다음 번호
	@Query("select nvl(max(c.commentaryNo), 0) + 1 from Commentary c")
	public int getNextNo();
	
	
	//댓글 작성
	/*
	@Modifying
	@Query(value = "insert into Commentary (commentary_no, commentary_date, commentary_content, community_no, users_no) values (:#{#c.commentaryNo}, sysdate, :#{#c.commentaryContent}, :#{#c.community.communityNo}, :#{#c.users.usersNo})", nativeQuery = true)
	@Transactional
	public void insert(Commentary c);
	*/
	
	@Modifying
	@Query(value = "insert into Commentary (commentary_no, commentary_date, commentary_content, community_no, users_no) values (:commentaryNo, sysdate, :commentaryContent, :communityNo, :usersNo)", nativeQuery = true)
	@Transactional
	public void insert(
	    @Param("commentaryNo") int commentaryNo,
	    @Param("commentaryContent") String commentaryContent,
	    @Param("communityNo") int communityNo,
	    @Param("usersNo") int usersNo
	);
	
	//커뮤니티 번호에 따른 댓글 조회
	@Query(value = "select c from Commentary c where c.community.communityNo = ?1")
	public List<Commentary> findAllByCommunityNo(int communityNo);

	//댓글 수정
	@Modifying
	@Query(value = "update Commentary set commentary_content=:commentaryContent where commentary_no=:commentaryNo", nativeQuery = true)
	@Transactional
	public void update(@Param("commentaryNo") int commentaryNo,@Param("commentaryContent") String commentaryContent);
}
