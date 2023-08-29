package com.example.demo.admin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Accomodation;

@Repository
public interface AdminAccomodationRepository extends JpaRepository<Accomodation, Integer> {

	 //activityName으로 검색
    Page<Accomodation> findByAccomodationNameContaining(String accomodationName, Pageable pageable);
    
    //activityAddr로 검색
    Page<Accomodation> findByAccomodationAddrContaining(String accomodationAddr, Pageable pageable);

}
