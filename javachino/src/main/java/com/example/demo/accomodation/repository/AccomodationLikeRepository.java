package com.example.demo.accomodation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Accomodation;
import com.example.demo.entity.AccomodationLike;
import com.example.demo.entity.Users;

@Repository
public interface AccomodationLikeRepository extends JpaRepository<AccomodationLike, Integer>{

	
	@Modifying
	@Query(value = "insert into accomodation_like (ACCOMODATION_NO, USERS_NO, ACCMODATION_LIKE_NO) values (?1, ?2, ACCOMODATION_LIKE_SEQ.nextval)", nativeQuery = true)
	public void insertAccomodationLike(int activityNo, int usersNo);
	
	public boolean existsByUsersAndAccomodation(Users users, Accomodation accomodation);
    void deleteByUsersAndAccomodation(Users users, Accomodation accomodation);
}
