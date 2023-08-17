package com.example.demo.admin.dao;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.admin.dto.AdminActivityRvDto;
import com.example.demo.entity.ActivityRv;

import jakarta.transaction.Transactional;

@Repository
public interface AdminActivityRvDAO extends JpaRepository<ActivityRv, Integer> {
			
		@Query(value = "SELECT act.activity_rv_no, act.activity_name, act.rv_date, act.activity_rv_date, act.activity_rv_people, act.activity_rv_phone, act.users_name "
				+ "FROM ( "
				+ "SELECT ROWNUM AS n, ar.activity_rv_no, a.activity_name, ar.rv_date, ar.activity_rv_date, ar.activity_rv_people, ar.activity_rv_phone, u.users_name "
				+ "FROM activity_rv ar, activity a, users u "
				+ "WHERE ar.users_no = u.users_no AND ar.activity_no = a.activity_no "
				+ ") act "
				+ "WHERE act.n BETWEEN ?1 AND ?2 "
				+ "ORDER BY act.activity_rv_no",nativeQuery = true)
		public List<AdminActivityRvDto> getAllActivityRvDto(int start, int end);
	
		//이름으로 검색
		@Query("SELECT new com.example.demo.admin.dto.AdminActivityRvDto(ar.activityRvNo, ar.activity.activityName, ar.rvDate, ar.activityRvDate, ar.activityRvPhone, ar.users.usersName) FROM ActivityRv ar WHERE ar.activity.activityName LIKE %:activityName%")
	    public List<AdminActivityRvDto> findByActivityNameLike(@Param("activityName") String activityName);
		
		//아이디로 검색
		@Query("SELECT new com.example.demo.admin.dto.AdminActivityRvDto(ar.activityRvNo, ar.activity.activityName, ar.rvDate, ar.activityRvDate, ar.activityRvPhone, ar.users.usersName) FROM ActivityRv ar WHERE ar.users.usersName LIKE %:usersName%")
	    public List<AdminActivityRvDto> findByUsersNameLike(@Param("usersName") String usersName);
		
		
		@Query(value ="select count(*) from Users u where activity_name like ?1", nativeQuery = true)
		public int countByActivityName(String keyword);
		   
		@Query(value ="select count(*) from Users u where users_name like ?1", nativeQuery = true)
		public int countByUsersName(String keyword);
		
		//수정
		@Modifying
	    @Query(value = "UPDATE ActivityRv ar SET ar.rvDate = :rvDate, ar.activityRvDate = :activityRvDate, ar.activityRvPeople = :activityRvPeople, ar.activityRvPhone = :activityRvPhone WHERE ar.activityRvNo = :activityRvNo")
	    @Transactional
	    void update(
	        @Param("activityRvNo") int activityRvNo,
	        @Param("rvDate") LocalDate rvDate,
	        @Param("activityRvDate") LocalDate activityRvDate,
	        @Param("activityRvPeople") int activityRvPeople,
	        @Param("activityRvPhone") String activityRvPhone
	    );
		
}