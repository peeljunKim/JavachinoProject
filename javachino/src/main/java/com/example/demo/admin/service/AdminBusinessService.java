package com.example.demo.admin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.admin.repository.AdminBusinessRepository;
import com.example.demo.entity.Business;

import lombok.Setter;

@Service
@Setter
public class AdminBusinessService {
	private final AdminBusinessRepository adminRepository;

    public AdminBusinessService(AdminBusinessRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    //테이블 출력
    @Transactional(readOnly = true)
    public Page<Business> findAll(Pageable pageable) {
        return adminRepository.findAll(pageable);
    }
    //name 검색기능
    @Transactional(readOnly = true)
    public Page<Business> findByBusinessNameContaining(String businessName, Pageable pageable) {
        return adminRepository.findByBusinessNameContaining(businessName, pageable);
    }
    //addr 검색기능
    @Transactional(readOnly = true)
    public Page<Business> findByBusinessAddrContaining(String businessAddr, Pageable pageable) {
        return adminRepository.findByBusinessAddrContaining(businessAddr, pageable);
    }
    //phone 검색기능
    @Transactional(readOnly = true)
    public Page<Business> findByBusinessPhoneContaining(String businessphone, Pageable pageable) {
    	return adminRepository.findByBusinessPhoneContaining(businessphone, pageable);
    }
    //manager 검색기능
    @Transactional(readOnly = true)
    public Page<Business> findByBusinessManagerContaining(String businessManager, Pageable pageable) {
    	return adminRepository.findByBusinessManagerContaining(businessManager, pageable);
    }
    
    @Transactional
    public Business save(Business business) {
        return adminRepository.save(business);
    }
    //수정기능
    @Transactional
    public Business update(Integer id, Business updatedBusiness) {
        return adminRepository.findById(id)
                .map(business -> {
                	business.setBusinessName(updatedBusiness.getBusinessName());
                	business.setBusinessAddr(updatedBusiness.getBusinessAddr());
                	business.setBusinessPhone(updatedBusiness.getBusinessPhone());
                	business.setBusinessManager(updatedBusiness.getBusinessManager());
                	business.setBusinessCategory(updatedBusiness.getBusinessCategory());
                    
                    return adminRepository.save(business);
                })
                .orElseThrow(() -> new IllegalArgumentException("Invalid business Id:" + id));
    }
    //삭제기능
    @Transactional
    public void delete(Integer id) {
        adminRepository.deleteById(id);
    }
	
}
