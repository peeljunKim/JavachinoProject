package com.example.demo.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Accomodation;
import com.example.demo.entity.AccomodationInfo;
import com.example.demo.entity.AccomodationReiview;

import jakarta.transaction.Transactional;

@Repository
public interface AdminAccomodationInfoDAO extends JpaRepository<AccomodationInfo, Integer> {
	//페이징
		@Query(value = "select accomodation_no,accomodation_name,accomodation_addr,accomodation_price,accomodation_category from (select rownum n,accomodation_no,accomodation_name,accomodation_addr,accomodation_price,accomodation_category from accomodation) where n between ?1 and ?2 order by accomodation_no", nativeQuery = true)
		public List<AccomodationInfo> selectAll(int start, int end);
		
//		//이름으로 검색
//		public List<Accomodation> 
//		findByUsersNameLike(String accomodationName);
//		
//		//아이디로 검색
//		public List<Accomodation>
//		findByUsersIdLike(String accomodationAddr);
		
//		
//		@Query(value ="select count(*) from Users u where users_id like ?1", nativeQuery = true)
//		public int countByUsersId(String keyword);
//		   
//		@Query(value ="select count(*) from Users u where users_name like ?1", nativeQuery = true)
//		public int countByUsersName(String keyword);
//		

//		//수정
//		@Modifying
//		@Query(value = "UPDATE users u set u.users_fname = :usersFname, u.users_phone = :usersPhone where u.users_no = :usersNo", nativeQuery = true)
//		@Transactional
//		void update(@Param("usersNo") int usersNo, @Param("usersFname") String usersFname, @Param("usersPhone") String usersPhone);
//		//삭제
//		@Modifying
//		@Query(value="delete Users u where u.users_no=?1", nativeQuery = true)
//		@Transactional
//		public int deleteUsers(int usersNo);
	
}