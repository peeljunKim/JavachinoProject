package com.example.demo.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Business;

import jakarta.transaction.Transactional;

@Repository
public interface AdminBusinessDAO extends JpaRepository<Business, Integer> {
	//페이징
	@Query(value = "select business_no,business_name,business_addr,business_phone,business_manager,business_category from (select rownum n,business_no,business_name,business_addr,business_phone,business_manager,business_category from business)b where n between ?1 and ?2 order by business_no", nativeQuery = true)
	public List<Business> selectAll(int start, int end);
	
	//이름으로 검색
	public List<Business> 
	findByBusinessNameLike(String businessName);
	//주소로 검색
	public List<Business>
	findByBusinessAddrLike(String businessAddr);
	//번호로 검색
	public List<Business>
	findByBusinessPhoneLike(String businessPhone);
	//담당자로 검색
	public List<Business>
	findByBusinessManagerLike(String businessManager);
	
	
	@Query(value ="select count(*) from business b where business_name like ?1", nativeQuery = true)
	public int countByBusinessName(String keyword);
	   
	@Query(value ="select count(*) from business b where business_addr like ?1", nativeQuery = true)
	public int countByBusinessAddr(String keyword);
	
	@Query(value ="select count(*) from business b where business_phone like ?1", nativeQuery = true)
	public int countByBusinessPhone(String keyword);
	
	@Query(value ="select count(*) from business b where business_manager like ?1", nativeQuery = true)
	public int countByBusinessManager(String keyword);
	
	
	
	
	//추가
	@Modifying
	@Query(value = "INSERT into business (business_no, business_name,business_addr, business_phone, business_manager, business_category) values (BUSINESS_SEQ.NEXTVAL, :#{#b.businessName}, :#{#b.businessAddr},:#{#b.businessPhone}, :#{#b.businessManager}, :#{#b.businessCategory.ordinal()})", nativeQuery = true)
	@Transactional
	void insert(@Param("b") Business b);
	//수정
	@Modifying
	@Query(value = "UPDATE business b set b.business_name = :businessName, b.business_addr = :businessAddr, b.business_phone = :businessPhone, b.business_manager = :businessManager where b.business_no = :businessNo", nativeQuery = true)
	@Transactional
	void update(@Param("businessNo") int businessNo, @Param("businessName") String businessName, @Param("businessAddr") String businessAddr, @Param("businessPhone") String businessPhone, @Param("businessManager") String businessManager);
	//삭제
	@Modifying
	@Query(value="delete Business b where b.business_no=?1", nativeQuery = true)
	@Transactional
	public int deleteBusiness(int businessNo);
	
}
