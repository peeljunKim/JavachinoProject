package com.example.demo.community.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Community;

import jakarta.transaction.Transactional;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Integer> {
	
	public List<Community> findAllByOrderByCommunityDateDesc();
	
	public List<Community> findAllByOrderByCommunityNoDesc();
	
	//게시글 페이징...(최신순)
	@Query(value="select * from "
			+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_no,accomodation_rv_no,activity_rv_no,community_addr,community_content,community_hit "
			+ "from (select * from community "
			+ "order by community_date desc)) a "
			+ "where a.n between ?1 and ?2", nativeQuery = true)
	public List<Community> selectAllByOrderByCommunityDate(int start, int end);
	
	//게시글 페이징...(추천순)
		@Query(value="select * from "
				+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_no,accomodation_rv_no,activity_rv_no,community_addr,community_content,community_hit "
				+ "from (select * from community "
				+ "order by community_hit desc)) a "
				+ "where a.n between ?1 and ?2", nativeQuery = true)
		public List<Community> selectAllByOrderByCommunityHit(int start, int end);
	
	//제목으로 검색
	@Query(value="select * from "
			+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_no,accomodation_rv_no,activity_rv_no,community_addr,community_content,community_hit "
			+ "from (select * from community where community_title like ?3 "
			+ "order by community_no desc)) a "
			+ "where a.n between ?1 and ?2", nativeQuery = true)
	public List<Community> findByCommunityTitle(int start, int end, String communityTitle);
	
	//지역으로 검색
	@Query(value="select * from "
			+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_no,accomodation_rv_no,activity_rv_no,community_addr,community_content,community_hit "
			+ "from (select * from community where community_addr like ?3 "
			+ "order by community_no desc)) a "
			+ "where a.n between ?1 and ?2", nativeQuery = true)
	public List<Community> findByCommunityAddr(int start, int end, String communityAddr);
		
	//제목으로 검색 + 최신순 + 페이징
	@Query(value="select * from "
			+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_no,accomodation_rv_no,activity_rv_no,community_addr,community_content,community_hit "
			+ "from (select * from community where community_title like ?3 "
			+ "order by community_date desc)) a "
			+ "where a.n between ?1 and ?2", nativeQuery = true)
	public List<Community> findByCommunityTitleOrderByCommunityDate(int start, int end, String communityTitle);
	
	//제목으로 검색 + 추천순 + 페이징
	@Query(value="select * from "
			+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_no,accomodation_rv_no,activity_rv_no,community_addr,community_content,community_hit "
			+ "from (select * from community where community_title like ?3 "
			+ "order by community_hit desc)) a "
			+ "where a.n between ?1 and ?2", nativeQuery = true)
	public List<Community> findByCommunityTitleOrderByCommunityHit(int start, int end, String communityTitle);

	//지역으로 검색 + 최신순 + 페이징
	@Query(value="select * from "
			+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_no,accomodation_rv_no,activity_rv_no,community_addr,community_content,community_hit "
			+ "from (select * from community where community_addr like ?3 "
			+ "order by community_date desc)) a "
			+ "where a.n between ?1 and ?2", nativeQuery = true)
	public List<Community> findByCommunityAddrOrderByCommunityDate(int start, int end, String communityTitle);

	//지역으로 검색 + 추천순 + 페이징
	@Query(value="select * from "
			+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_no,accomodation_rv_no,activity_rv_no,community_addr,community_content,community_hit "
			+ "from (select * from community where community_addr like ?3 "
			+ "order by community_hit desc)) a "
			+ "where a.n between ?1 and ?2", nativeQuery = true)
	public List<Community> findByCommunityAddrOrderByCommunityHit(int start, int end, String communityTitle);

	
	//제목으로 검색(blog)
		@Query(value="select * from "
				+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_no,accomodation_rv_no,activity_rv_no,community_addr,community_content,community_hit "
				+ "from (select * from community where community_select = 0 and community_title like ?3 "
				+ "order by community_no desc)) a "
				+ "where a.n between ?1 and ?2", nativeQuery = true)
		public List<Community> findByCommunityTitleBlog(int start, int end, String communityTitle);
		
		//지역으로 검색(blog)
		@Query(value="select * from "
				+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_no,accomodation_rv_no,activity_rv_no,community_addr,community_content,community_hit "
				+ "from (select * from community where community_select = 0 and community_addr like ?3 "
				+ "order by community_no desc)) a "
				+ "where a.n between ?1 and ?2", nativeQuery = true)
		public List<Community> findByCommunityAddrBlog(int start, int end, String communityAddr);
	
		//제목으로 검색 + 최신순 + 페이징(blog)
		@Query(value="select * from "
				+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_no,accomodation_rv_no,activity_rv_no,community_addr,community_content,community_hit "
				+ "from (select * from community where community_select = 0 and community_title like ?3 "
				+ "order by community_date desc)) a "
				+ "where a.n between ?1 and ?2", nativeQuery = true)
		public List<Community> findBlogByCommunityTitleOrderByCommunityDate(int start, int end, String communityTitle);
		
		//제목으로 검색 + 추천순 + 페이징(blog)
		@Query(value="select * from "
				+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_no,accomodation_rv_no,activity_rv_no,community_addr,community_content,community_hit "
				+ "from (select * from community where community_select = 0 and community_title like ?3 "
				+ "order by community_hit desc)) a "
				+ "where a.n between ?1 and ?2", nativeQuery = true)
		public List<Community> findBlogyCommunityTitleOrderByCommunityHit(int start, int end, String communityTitle);

		//지역으로 검색 + 최신순 + 페이징(blog)
		@Query(value="select * from "
				+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_no,accomodation_rv_no,activity_rv_no,community_addr,community_content,community_hit "
				+ "from (select * from community where community_select = 0 and community_addr like ?3 "
				+ "order by community_date desc)) a "
				+ "where a.n between ?1 and ?2", nativeQuery = true)
		public List<Community> findBlogByCommunityAddrOrderByCommunityDate(int start, int end, String communityTitle);

		//지역으로 검색 + 추천순 + 페이징(blog)
		@Query(value="select * from "
				+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_no,accomodation_rv_no,activity_rv_no,community_addr,community_content,community_hit "
				+ "from (select * from community where community_select = 0 and community_addr like ?3 "
				+ "order by community_hit desc)) a "
				+ "where a.n between ?1 and ?2", nativeQuery = true)
		public List<Community> findBlogByCommunityAddrOrderByCommunityHit(int start, int end, String communityTitle);
		
		//게시글 페이징...(최신순)(blog)
		@Query(value="select * from "
				+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_no,accomodation_rv_no,activity_rv_no,community_addr,community_content,community_hit "
				+ "from (select * from community "
				+ "order by community_date desc)) a "
				+ "where a.n between ?1 and ?2", nativeQuery = true)
		public List<Community> selectAllBlogByOrderByCommunityDate(int start, int end);
		
		//게시글 페이징...(추천순)(blog)
			@Query(value="select * from "
					+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_no,accomodation_rv_no,activity_rv_no,community_addr,community_content,community_hit "
					+ "from (select * from community "
					+ "order by community_hit desc)) a "
					+ "where a.n between ?1 and ?2", nativeQuery = true)
			public List<Community> selectAllBlogByOrderByCommunityHit(int start, int end);
		
	//조회수3위
	@Query(value="select * from "
			+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_no,accomodation_rv_no,activity_rv_no,community_addr,community_content,community_hit "
			+ "from (select * from community "
			+ "order by community_hit desc)) a "
			+ "where a.n between 1 and 3", nativeQuery = true)
	public List<Community> selectThird();

	//1위
		@Query(value="select * from community where community_no = 5", nativeQuery = true)
		public Community selectFirst();
	
	//게시글 다음 번호
	@Query("select nvl(max(c.communityNo), 0) + 1 from Community c")
	public int getNextNo();

	//게시글 등록
	@Modifying
	@Query(value = "insert into Community (community_no, community_date, community_title, community_addr, community_content, community_category, community_select, community_hit, users_no) values (:#{#c.communityNo}, sysdate, :#{#c.communityTitle}, :#{#c.communityAddr}, :#{#c.communityContent}, :#{#c.communityCategory.ordinal()}, :#{#c.communitySelect.ordinal()}, 0, :#{#c.users.usersNo})", nativeQuery = true)
	@Transactional
	public void insert(Community c);

	//숙소 예약있을때 게시글 등록
	@Modifying
	@Query(value = "insert into Community (community_no, community_date, community_title, community_addr, community_content, community_category, community_select, community_hit, users_no,  accomodation_rv_no) values (:#{#c.communityNo}, sysdate, :#{#c.communityTitle}, :#{#c.communityAddr}, :#{#c.communityContent}, :#{#c.communityCategory.ordinal()}, :#{#c.communitySelect.ordinal()}, 0, :#{#c.users.usersNo}, :#{#c.accomodationRv.accomodationRVNo})", nativeQuery = true)
	@Transactional
	public void insertWithAccom(Community c);
	
	//액티비티 예약있을때 게시글 등록
	@Modifying
	@Query(value = "insert into Community (community_no, community_date, community_title, community_addr, community_content, community_category, community_select, community_hit, users_no, activity_rv_no) values (:#{#c.communityNo}, sysdate, :#{#c.communityTitle}, :#{#c.communityAddr}, :#{#c.communityContent}, :#{#c.communityCategory.ordinal()}, :#{#c.communitySelect.ordinal()}, 0, :#{#c.users.usersNo}, :#{#c.activityRv.activityRvNo})", nativeQuery = true)
	@Transactional
	public void insertWithActivity(Community c);

	//게시글 상세
	public Community findById(int CommunityNo);
	
	//게시글 수정
	@Modifying
	@Query(value = "update Community c set c.community_title = :#{#c.communityTitle}, c.community_addr=:#{#c.communityAddr}, c.community_content=:#{#c.communityContent}, c.community_category = :#{#c.communityCategory.ordinal()}, c. community_select = :#{#c.communitySelect.ordinal()} where c.community_no=:#{#c.communityNo}", nativeQuery = true)
	@Transactional
	public void update(Community c);

	
	@Query(value ="select count(*) from Community c where community_title like ?1", nativeQuery = true)
	public int countByCommunityTitle(String keyword);
	
	@Query(value ="select count(*) from Community c where community_addr like ?1", nativeQuery = true)
	public int countByCommunityAddr(String keyword);
	
	//게시글 조회수 올리기
	@Modifying
	@Query(value = "update Community c set c.community_hit = c.community_hit+1 where c.community_no=:communityNo", nativeQuery = true)
	@Transactional
	public void updateHit(@Param("communityNo") int communityNo);

}
