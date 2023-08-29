package com.example.demo.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.admin.repository.AdminAccomodationRepository;
import com.example.demo.entity.Accomodation;
import com.example.demo.entity.Users;

import lombok.Setter;

@Setter
@Service
public class AdminAccomodationService {
    private final AdminAccomodationRepository accomodationRepository;
    
    public AdminAccomodationService(AdminAccomodationRepository accomodationRepository) {
        this.accomodationRepository = accomodationRepository;
    }
    //테이블
    public Page<Accomodation> findAll(Pageable pageable) {
        return accomodationRepository.findAll(pageable);
    }
    //name 검색기능
    @Transactional(readOnly = true)
    public Page<Accomodation> findByAccomodationNameContaining(String accomodationName, Pageable pageable) {
        return accomodationRepository.findByAccomodationNameContaining(accomodationName, pageable);
    }
    //addr 검색기능
    @Transactional(readOnly = true)
    public Page<Accomodation> findByAccomodationAddrContaining(String accomodationAddr, Pageable pageable) {
        return accomodationRepository.findByAccomodationAddrContaining(accomodationAddr, pageable);
    }
    
    @Transactional
    public Accomodation save(Accomodation accomodation) {
        return accomodationRepository.save(accomodation);
    }
    //수정기능
    @Transactional
    public Accomodation update(Integer id, Accomodation updatedAccomodation) {
        return accomodationRepository.findById(id)
                .map(accomodation -> {
                	accomodation.setAccomodationName(updatedAccomodation.getAccomodationName());
                	accomodation.setAccomodationAddr(updatedAccomodation.getAccomodationAddr());
                	accomodation.setAccomodationPrice(updatedAccomodation.getAccomodationPrice());
                	accomodation.setAccomodationCategory(updatedAccomodation.getAccomodationCategory());
                    
                    return accomodationRepository.save(accomodation);
                })
                .orElseThrow(() -> new IllegalArgumentException("Invalid accomodation Id:" + id));
    }
    //삭제기능
    @Transactional
    public void delete(Integer id) {
    	accomodationRepository.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public List<Accomodation> findAll() {
        return accomodationRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Optional<Accomodation> findById(Integer id) {
        return accomodationRepository.findById(id);
    }


}
