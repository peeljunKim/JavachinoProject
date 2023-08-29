package com.example.demo.admin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AccomodationRV;

@Repository
public interface AdminAccomodationRvRepository extends JpaRepository<AccomodationRV, Integer> {
	 Page<AccomodationRV> findAll(Pageable pageable);
	    
	    //accomodation로 검색
	    Page<AccomodationRV> findByAccomodation_AccomodationNameContaining(String accomodationName, Pageable pageable);
	    
	    //users의 name으로 검색
	    Page<AccomodationRV> findByUsers_UsersNameContaining(String usersName, Pageable pageable);
	    
	    
	    //Accomodation와 Users 엔티티를 참조하고 있습니다. 이럴 경우에는 단순히 String으로 검색하는 것이 아니라 해당 엔티티를 인자로 받아야 합니다.
	
}