package com.example.demo.community.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Activity;

@Repository
public interface MainActivityRepository extends JpaRepository<Activity, Integer> {

	//4위
		@Query(value="select * from "
				+ " (select rownum n, activity_no, activity_name, activity_addr, activity_explanation, activity_category, activity_price,activity_time,activity_fname1,activity_fname2,activity_fname3,business_no "
				+ "from (select * from activity "
				+ "order by activity_no desc)) a "
				+ "where a.n between 1 and 4", nativeQuery = true)
		public List<Activity> findAllRownum();
		
		@Query(value="select * from "
				+ " (select rownum n, activity_no, activity_name, activity_addr, activity_explanation, activity_category, activity_price,activity_time,activity_fname1,activity_fname2,activity_fname3,business_no "
				+ "from (select * from activity "
				+ "order by activity_no desc)) a "
				+ "where a.n between 10 and 13", nativeQuery = true)
		public List<Activity> findBy();

		//지역검색
		@Query(value="select * from activity where activity_addr like ?1", nativeQuery = true)
		public List<Activity> findByActivityAddr(String keyword);
}
