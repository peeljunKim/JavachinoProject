package com.example.demo.accomodation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AccomodationFile;

@Repository
public interface AccomodationFileRepository extends JpaRepository<AccomodationFile, Integer> {

	public AccomodationFile findByAccomodation_AccomodationNo(int accomodationNo);
}
