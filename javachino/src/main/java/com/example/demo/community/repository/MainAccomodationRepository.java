package com.example.demo.community.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Accomodation;

@Repository
public interface MainAccomodationRepository extends JpaRepository<Accomodation, Integer> {

	//4위
			@Query(value="select * from "
					+ " (select rownum n, accomodation_no, accomodation_name, accomodation_addr, accomodation_price, accomodation_category, business_no "
					+ "from (select * from accomodation "
					+ "order by accomodation_no desc)) a "
					+ "where a.n between 1 and 4", nativeQuery = true)
			public List<Accomodation> findAllRownum();
			
	@Query(value="select * from accomodation where accomodation_addr like ?1", nativeQuery = true)
	public List<Accomodation> findByAccomodationAddr(String keyword);
}
