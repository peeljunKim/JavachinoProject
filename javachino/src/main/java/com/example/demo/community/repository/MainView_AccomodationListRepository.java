package com.example.demo.community.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Accomodation;
import com.example.demo.entity.AccomodationInfo;
import com.example.demo.entity.View_AccomodationList;


@Repository
public interface MainView_AccomodationListRepository extends JpaRepository<View_AccomodationList, Integer> {

	@Query(value =  "SELECT ACCOMODATION_NO FROM view_accomodation_list", nativeQuery = true)
    public List<Integer> findAllAccomodationNumbers();
	
	public List<View_AccomodationList> findByAccomodationNoIn(Collection<Integer> accomodationNos);

	public List<View_AccomodationList> findByAccomodationAddrContaining(String addr);
	
	@Query(value = "SELECT a.* FROM view_accomodation_list a WHERE a.ACCOMODATION_NO >= 1 and a.ACCOMODATION_NO <= 4", nativeQuery = true)
    public List<View_AccomodationList> findAccomodationByNo();

    @Query(value = "SELECT a.* FROM view_accomodation_list a WHERE a.ACCOMODATION_NO = ?1", nativeQuery = true)
    public View_AccomodationList findAccomodationByNo(int accomodationNo);
	
}
