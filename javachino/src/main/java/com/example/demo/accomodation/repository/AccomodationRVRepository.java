package com.example.demo.accomodation.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AccomodationRV;

import jakarta.transaction.Transactional;

@Repository
public interface AccomodationRVRepository extends JpaRepository<AccomodationRV, Integer>{
	
	

	@Query(value = "SELECT DISTINCT a.accomodation_no, a.accomodation_rv_no "
			+ "FROM accomodation_rv a "
			+ "WHERE "
			+ "    (a.accomodation_rv_checkin <= TO_DATE(?1, 'YYYY/MM/DD') AND a.accomodation_rv_checkout > TO_DATE(?2, 'YYYY/MM/DD')) "
			+ "    OR "
			+ "    (a.accomodation_rv_checkin = TO_DATE(?1, 'YYYY/MM/DD')) ", nativeQuery = true)
	public List<Integer> findAccommodationNumbersByDateRange(String startDate, String endDate);
	
	@Query(value = "SELECT TO_CHAR(accomodation_rv_checkin, 'YYYY/MM/DD') || '~' || TO_CHAR(accomodation_rv_checkout, 'YYYY/MM/DD') AS range "
	        + "FROM accomodation_rv "
	        + "WHERE accomodation_no = ?1", nativeQuery = true)
	public List<String> getDateRangesByAccomodationNo(int accomodationNo);
	
	@Transactional
    @Modifying
    @Query(value = "INSERT INTO ACCOMODATION_RV (ACCOMODATION_NO, ACCOMODATION_RV_NO, USERS_NO, ACCOMODATION_RV_CHECKIN, ACCOMODATION_RV_CHECKOUT, ACCOMODATION_RV_DATE, ACCOMODATION_RV_NAME, ACCOMODATION_RV_PEOPLE, ACCOMODATION_RV_PHONE, ACCOMODATION_RV_People)"
            + "VALUES (?1, ACCOMODATION_RV_SEQ.nextval, ?2, ?3, ?4, CURRENT_DATE, ?5, ?6, ?7)", nativeQuery = true)
    public void insertAccomodationRv(int accomodationNo, int usersNo, String checkin, String chekcout, String name, String phone, String people);


	

}
