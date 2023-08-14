package com.example.demo.admin.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Business;

@Repository
public interface BusinessDAO extends JpaRepository<Business, Integer> {
	
	@Query(value = "select business_no,business_name,business_addr,business_phone,business_manager,business_category from (select rownum n,business_no,business_name,business_addr,business_phone,business_manager,business_category from business)b where n between ?1 and ?2", nativeQuery = true)
	public List<Business> selectAll(int start, int end);
	
	
	
}
