package com.example.demo.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.admin.dto.AdminActivityDto;
import com.example.demo.entity.Activity;

import jakarta.transaction.Transactional;

@Repository
public interface AdminActivityDAO extends JpaRepository<Activity, Integer> {
		//페이징
		@Query(value = "SELECT new com.example.demo.admin.dto.AdminActivityDto(a.activityNo, a.activityName, a.activityAddr, a.activityExplanation, a.activityPrice, a.activityTime, a.activityFname1, a.activityFname2, a.activityFname3) FROM Activity a WHERE ROWNUM BETWEEN ?1 AND ?2 ORDER BY a.activityNo")
		public List<AdminActivityDto> selectPartialData(int start, int end);
		
		//이름으로 검색
		public List<AdminActivityDto> 
		findByActivityNameLike(String activityName);
		
		//주소로 검색
		public List<AdminActivityDto>
		findByActivityAddrLike(String activityAddr);
		
		
	    // Count by activity name
	    @Query(value = "SELECT COUNT(*) FROM Activity a WHERE a.activityName LIKE %?1%", nativeQuery = true)
	    public int countByActivityName(String keyword);

	    // Count by activity address
	    @Query(value = "SELECT COUNT(*) FROM Activity a WHERE a.activityAddr LIKE %?1%", nativeQuery = true)
	    public int countByActivityAddr(String keyword);
		
		
		
		//추가
		@Modifying
	    @Query(value = "INSERT INTO activity (activity_no, activity_name, activity_addr, activity_explanation, activity_category, activity_price, activity_time, activity_fname1, activity_fname2, activity_fname3) VALUES (ACTIVITY_SEQ.NEXTVAL, :#{#a.activityName}, :#{#a.activityAddr}, :#{#a.activityExplanation}, :#{#a.activityCategory.ordinal()}, :#{#a.activityPrice}, :#{#a.activityTime}, :#{#a.activityFname1}, :#{#a.activityFname2}, :#{#a.activityFname3})", nativeQuery = true)
	    @Transactional
	    void insert(@Param("a") Activity a);
		//수정
		@Modifying
		@Query(value = "UPDATE Activity a set a.activity_name = :activityName, a.activity_addr = :activityAddr, a.activity_explanation = :activityExplanation, a.activity_price = :activityPrice, a.activity_time = :activityTime, a.activity_fname1 = :activityFname1, a.activity_fname2 = :activityFname2, a.activity_fname3 = :activityFname3  where a.activity_no = :activityNo", nativeQuery = true)
		@Transactional
		void update(@Param("activityNo") int activityNo, @Param("activityName") String activityName, @Param("activityAddr") String activityAddr, @Param("activityExplanation") String activityExplanation, @Param("activityPrice") String activityPrice, @Param("activityTime") String activityTime, @Param("activityFname1") String activityFname1, @Param("activityFname2") String activityFname2, @Param("activityFname3") String activityFname3);
		//삭제
		@Modifying
		@Query(value="delete Activity a where a.activity_no=?1", nativeQuery = true)
		@Transactional
		public int deleteActivity(int activityNo);
}