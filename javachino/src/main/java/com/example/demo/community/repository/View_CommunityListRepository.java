package com.example.demo.community.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Community;
import com.example.demo.entity.View_CommunityList;

@Repository
public interface View_CommunityListRepository extends JpaRepository<View_CommunityList, Integer> {

	//전체
		//게시글 페이징...(최신순)
		@Query(value="select * from "
				+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_name, community_like ,community_addr,commentary,community_hit "
				+ "from (select * from view_community_list "
				+ "order by community_date desc)) a "
				+ "where a.n between ?1 and ?2", nativeQuery = true)
		public List<View_CommunityList> selectAllByOrderByCommunityDate(int start, int end);

		//게시글 페이징...(추천순)
		@Query(value="select * from "
				+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_name, community_like ,community_addr,commentary,community_hit "
				+ "from (select * from view_community_list "
				+ "order by community_hit desc)) a "
				+ "where a.n between ?1 and ?2", nativeQuery = true)
		public List<View_CommunityList> selectAllByOrderByCommunityHit(int start, int end);
		
		
		//제목으로 검색 + 최신순 + 페이징
		@Query(value="select * from "
				+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_name, community_like ,community_addr,commentary,community_hit "
				+ "from (select * from view_community_list where community_title like ?3 "
				+ "order by community_date desc)) a "
				+ "where a.n between ?1 and ?2", nativeQuery = true)
		public List<View_CommunityList> findByCommunityTitleOrderByCommunityDate(int start, int end, String communityTitle);
		
		//제목으로 검색 + 추천순 + 페이징
		@Query(value="select * from "
				+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_name, community_like ,community_addr,commentary,community_hit "
				+ "from (select * from view_community_list where community_title like ?3 "
				+ "order by community_hit desc)) a "
				+ "where a.n between ?1 and ?2", nativeQuery = true)
		public List<View_CommunityList> findByCommunityTitleOrderByCommunityHit(int start, int end, String communityTitle);
		
		//지역으로 검색 + 최신순 + 페이징
		@Query(value="select * from "
				+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_name, community_like ,community_addr,commentary,community_hit "
				+ "from (select * from view_community_list where community_addr like ?3 "
				+ "order by community_date desc)) a "
				+ "where a.n between ?1 and ?2", nativeQuery = true)
		public List<View_CommunityList> findByCommunityAddrOrderByCommunityDate(int start, int end, String communityAddr);

		//지역으로 검색 + 추천순 + 페이징
		@Query(value="select * from "
				+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_name, community_like ,community_addr,commentary,community_hit "
				+ "from (select * from view_community_list where community_addr like ?3 "
				+ "order by community_hit desc)) a "
				+ "where a.n between ?1 and ?2", nativeQuery = true)
		public List<View_CommunityList> findByCommunityAddrOrderByCommunityHit(int start, int end, String communityAddr);
		
		
		//제목으로 검색된 게시글 수 
		@Query(value ="select count(*) from view_community_list c where community_title like ?1", nativeQuery = true)
		public int countByCommunityTitle(String keyword);
		
		//지역으로 검색된 게시글 수 
		@Query(value ="select count(*) from view_community_list c where community_addr like ?1", nativeQuery = true)
		public int countByCommunityAddr(String keyword);
		
	//블로그
		//제목으로 검색 + 최신순 + 페이징(blog)
		@Query(value="select * from "
				+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_name, community_like ,community_addr,commentary,community_hit "
				+ "from (select * from view_community_list where community_select = 0 and community_title like ?3 "
				+ "order by community_date desc)) a "
				+ "where a.n between ?1 and ?2", nativeQuery = true)
		public List<View_CommunityList> findBlogByCommunityTitleOrderByCommunityDate(int start, int end, String communityTitle);
		
		//제목으로 검색 + 추천순 + 페이징(blog)
		@Query(value="select * from "
				+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_name, community_like ,community_addr,commentary,community_hit "
				+ "from (select * from view_community_list where community_select = 0 and community_title like ?3 "
				+ "order by community_hit desc)) a "
				+ "where a.n between ?1 and ?2", nativeQuery = true)
		public List<View_CommunityList> findBlogyCommunityTitleOrderByCommunityHit(int start, int end, String communityTitle);

		//지역으로 검색 + 최신순 + 페이징(blog)
		@Query(value="select * from "
				+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_name, community_like ,community_addr,commentary,community_hit "
				+ "from (select * from view_community_list where community_select = 0 and community_addr like ?3 "
				+ "order by community_date desc)) a "
				+ "where a.n between ?1 and ?2", nativeQuery = true)
		public List<View_CommunityList> findBlogByCommunityAddrOrderByCommunityDate(int start, int end, String communityTitle);

		//지역으로 검색 + 추천순 + 페이징(blog)
		@Query(value="select * from "
				+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_name, community_like ,community_addr,commentary,community_hit "
				+ "from (select * from view_community_list where community_select = 0 and community_addr like ?3 "
				+ "order by community_hit desc)) a "
				+ "where a.n between ?1 and ?2", nativeQuery = true)
		public List<View_CommunityList> findBlogByCommunityAddrOrderByCommunityHit(int start, int end, String communityTitle);
		
		//게시글 페이징...(최신순)(blog)
		@Query(value="select * from "
				+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_name, community_like ,community_addr,commentary,community_hit "
				+ "from (select * from view_community_list where community_select = 0 "
				+ "order by community_date desc)) a "
				+ "where a.n between ?1 and ?2", nativeQuery = true)
		public List<View_CommunityList> selectAllBlogByOrderByCommunityDate(int start, int end);
		
		//게시글 페이징...(추천순)(blog)
		@Query(value="select * from "
				+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_name, community_like ,community_addr,commentary,community_hit "
				+ "from (select * from view_community_list where community_select = 0 "
				+ "order by community_hit desc)) a "
				+ "where a.n between ?1 and ?2", nativeQuery = true)
		public List<View_CommunityList> selectAllBlogByOrderByCommunityHit(int start, int end);
		
		//전체 게시글 수(blog)
		@Query(value ="select count(*) from view_community_list c where community_select = 0", nativeQuery = true)
		public int countBlog();
		
		//제목으로 검색된 게시글 수 
		@Query(value ="select count(*) from view_community_list c where community_select = 0 and community_title like ?1", nativeQuery = true)
		public int countBlogByCommunityTitle(String keyword);
		
		//지역으로 검색된 게시글 수 
		@Query(value ="select count(*) from view_community_list c where community_select = 0 and community_addr like ?1", nativeQuery = true)
		public int countBlogByCommunityAddr(String keyword);
		
	//게시판
		//제목으로 검색 + 최신순 + 페이징(board)
		@Query(value="select * from "
				+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_name, community_like ,community_addr,commentary,community_hit "
				+ "from (select * from view_community_list where community_select = 1 and community_title like ?3 "
				+ "order by community_date desc)) a "
				+ "where a.n between ?1 and ?2", nativeQuery = true)
		public List<View_CommunityList> findBoardByCommunityTitleOrderByCommunityDate(int start, int end, String communityTitle);
		
		//제목으로 검색 + 추천순 + 페이징(board)
		@Query(value="select * from "
				+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_name, community_like ,community_addr,commentary,community_hit "
				+ "from (select * from view_community_list where community_select = 1 and community_title like ?3 "
				+ "order by community_hit desc)) a "
				+ "where a.n between ?1 and ?2", nativeQuery = true)
		public List<View_CommunityList> findBoardyCommunityTitleOrderByCommunityHit(int start, int end, String communityTitle);

		//지역으로 검색 + 최신순 + 페이징(board)
		@Query(value="select * from "
				+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_name, community_like ,community_addr,commentary,community_hit "
				+ "from (select * from view_community_list where community_select = 1 and community_addr like ?3 "
				+ "order by community_date desc)) a "
				+ "where a.n between ?1 and ?2", nativeQuery = true)
		public List<View_CommunityList> findBoardByCommunityAddrOrderByCommunityDate(int start, int end, String communityTitle);

		//지역으로 검색 + 추천순 + 페이징(board)
		@Query(value="select * from "
				+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_name, community_like ,community_addr,commentary,community_hit "
				+ "from (select * from view_community_list where community_select = 1 and community_addr like ?3 "
				+ "order by community_hit desc)) a "
				+ "where a.n between ?1 and ?2", nativeQuery = true)
		public List<View_CommunityList> findBoardByCommunityAddrOrderByCommunityHit(int start, int end, String communityTitle);
		
		//게시글 페이징...(최신순)(board)
		@Query(value="select * from "
				+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_name, community_like ,community_addr,commentary,community_hit "
				+ "from (select * from view_community_list where community_select = 1 "
				+ "order by community_date desc)) a "
				+ "where a.n between ?1 and ?2", nativeQuery = true)
		public List<View_CommunityList> selectAllBoardByOrderByCommunityDate(int start, int end);
		
		//게시글 페이징...(추천순)(board)
		@Query(value="select * from "
				+ " (select rownum n, community_no, community_category, community_select, community_date, community_title, users_name, community_like ,community_addr,commentary,community_hit "
				+ "from (select * from view_community_list where community_select = 1 "
				+ "order by community_hit desc)) a "
				+ "where a.n between ?1 and ?2", nativeQuery = true)
		public List<View_CommunityList> selectAllBoardByOrderByCommunityHit(int start, int end);	
		
		//전체 게시글 수(board)
		@Query(value ="select count(*) from view_community_list c where community_select = 1", nativeQuery = true)
		public int countBoard();
		
		//제목으로 검색된 게시글 수(board)
		@Query(value ="select count(*) from view_community_list c where community_select = 1 and community_title like ?1", nativeQuery = true)
		public int countBoardByCommunityTitle(String keyword);
		
		//지역으로 검색된 게시글 수(board)
		@Query(value ="select count(*) from view_community_list c where community_select = 1 and community_addr like ?1", nativeQuery = true)
		public int countBoardByCommunityAddr(String keyword);
}
