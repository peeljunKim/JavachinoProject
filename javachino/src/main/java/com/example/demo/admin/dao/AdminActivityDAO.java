package com.example.demo.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Activity;

import jakarta.transaction.Transactional;

@Repository
public interface AdminActivityDAO extends JpaRepository<Activity, Integer> {
	//페이징
		@Query(value = "select activity_no, activity_name,activity_addr, activity_explanation, activity_category, activity_price, activity_time, activity_fname1, activity_fname2, activity_fname3, business_no from (select rownum n,activity_no, activity_name,activity_addr, activity_explanation, activity_category, activity_price, activity_time, activity_fname1, activity_fname2, activity_fname3, business_no from activity) where n between ?1 and ?2 order by activity_no", nativeQuery = true)
		public List<Activity> selectAll(int start, int end);
		
//		//이름으로 검색
//		public List<Users> 
//		findByUsersNameLike(String usersName);
//		
//		//아이디로 검색
//		public List<Users>
//		findByUsersIdLike(String usersId);
//		
//		
//		@Query(value ="select count(*) from Users u where users_id like ?1", nativeQuery = true)
//		public int countByUsersId(String keyword);
//		   
//		@Query(value ="select count(*) from Users u where users_name like ?1", nativeQuery = true)
//		public int countByUsersName(String keyword);
//		
		
		
		//추가
		@Modifying
	    @Query(value = "INSERT INTO activity (activity_no, activity_name, activity_addr, activity_explanation, activity_category, activity_price, activity_time, activity_fname1, activity_fname2, activity_fname3) VALUES (ACTIVITY_SEQ.NEXTVAL, :#{#a.activityName}, :#{#a.activityAddr}, :#{#a.activityExplanation}, :#{#a.activityCategory.ordinal()}, :#{#a.activityPrice}, :#{#a.activityTime}, :#{#a.activityFname1}, :#{#a.activityFname2}, :#{#a.activityFname3})", nativeQuery = true)
	    @Transactional
	    void insert(@Param("a") Activity a);
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