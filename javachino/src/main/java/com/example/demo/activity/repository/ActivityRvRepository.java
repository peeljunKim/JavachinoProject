package com.example.demo.activity.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ActivityRv;

import jakarta.transaction.Transactional;

@Repository
public interface ActivityRvRepository extends JpaRepository<ActivityRv, Integer> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO activity_rv (activity_rv_no, rv_date, activity_rv_date, activity_rv_people, activity_rv_phone, users_no, activity_no) "
            + "VALUES (ACTIVITY_RV_SEQ.nextval, CURRENT_DATE, :activityRvDate, :activityRvPeople, :activityRvPhone, :usersNo, :activityNo)", nativeQuery = true)
    void insertActivityRv(@Param("activityNo") int activityNo, @Param("usersNo") int usersNo, @Param("activityRvDate") LocalDate activityRvDate, @Param("activityRvPeople") int activityRvPeople, @Param("activityRvPhone") String activityRvPhone);
}
