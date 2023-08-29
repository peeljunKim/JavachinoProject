package com.example.demo.admin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Business;

@Repository
public interface AdminBusinessRepository extends JpaRepository<Business, Integer> {
    Page<Business> findAll(Pageable pageable);
    
    //businessName로 검색
    Page<Business> findByBusinessNameContaining(String businessName, Pageable pageable);
    
    //businessAddr로 검색
    Page<Business> findByBusinessAddrContaining(String businessAddr, Pageable pageable);
    
    //businessPhone로 검색
    Page<Business> findByBusinessPhoneContaining(String businessPhone, Pageable pageable);
   
    //businessManager로 검색
    Page<Business> findByBusinessManagerContaining(String businessManager, Pageable pageable);
}
