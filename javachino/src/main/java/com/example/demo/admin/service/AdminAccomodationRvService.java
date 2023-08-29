package com.example.demo.admin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.admin.repository.AdminAccomodationRvRepository;
import com.example.demo.entity.AccomodationRV;

import lombok.Setter;

@Service
@Setter
public class AdminAccomodationRvService {
	 private final AdminAccomodationRvRepository accomodationRvRepository;

	    public AdminAccomodationRvService(AdminAccomodationRvRepository accomodationRvRepository) {
	        this.accomodationRvRepository = accomodationRvRepository;
	    }
	    
	    //테이블 출력
	    @Transactional(readOnly = true)
	    public Page<AccomodationRV> findAll(Pageable pageable) {
	        return accomodationRvRepository.findAll(pageable);
	    }
	    
	    //name 검색기능
	    @Transactional(readOnly = true)
	    public Page<AccomodationRV> findByUsers_UsersNameContaining(String UsersName, Pageable pageable) {
	        return accomodationRvRepository.findByUsers_UsersNameContaining(UsersName, pageable);
	    }
	    
	    //name 검색기능
	    @Transactional(readOnly = true)
	    public Page<AccomodationRV> findByAccomodation_AccomodationNameContaining(String accomodationName, Pageable pageable) {
	        return accomodationRvRepository.findByAccomodation_AccomodationNameContaining(accomodationName, pageable);
	    }

	    
	    @Transactional
	    public AccomodationRV save(AccomodationRV accomodationRV) {
	        return accomodationRvRepository.save(accomodationRV);
	    }
	    //수정기능
	    @Transactional
	    public AccomodationRV update(Integer id, AccomodationRV updatedAccomodationRV) {
	        return accomodationRvRepository.findById(id)
	                .map(accomodationRV -> {
	                	accomodationRV.setAccomodationRVCheckIn(updatedAccomodationRV.getAccomodationRVCheckIn());
	                	accomodationRV.setAccomodationRVCheckOut(updatedAccomodationRV.getAccomodationRVCheckOut());
	                	accomodationRV.setAccomodationRVDate(updatedAccomodationRV.getAccomodationRVDate());
	                	accomodationRV.setAccomodationRVName(updatedAccomodationRV.getAccomodationRVName());
	                	accomodationRV.setAccomodationRVPeople(updatedAccomodationRV.getAccomodationRVPeople());
	                	accomodationRV.setAccomodationRVPhone(updatedAccomodationRV.getAccomodationRVPhone());
	                    
	                    return accomodationRvRepository.save(accomodationRV);
	                })
	                .orElseThrow(() -> new IllegalArgumentException("Invalid accomodationRV Id:" + id));
	    }
	    //삭제기능
	    @Transactional
	    public void delete(Integer id) {
	    	accomodationRvRepository.deleteById(id);
	    }

    public int getAccomodationTotalRecord() {
        return (int) accomodationRvRepository.count();
    }
}
