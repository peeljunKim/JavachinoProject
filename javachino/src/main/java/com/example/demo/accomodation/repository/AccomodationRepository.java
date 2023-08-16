package com.example.demo.accomodation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Accomodation;

@Repository
public interface AccomodationRepository extends JpaRepository<Accomodation, Integer> {

	@Query(value = "SELECT a.* FROM accomodation a WHERE a.ACCOMODATION_NO = ?1", nativeQuery = true)
    public Accomodation findAccomodationByNo(int accomodationNo);
}
