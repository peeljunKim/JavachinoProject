package com.example.demo.admin.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Users;

import jakarta.transaction.Transactional;

@Repository
public interface UsersDAO extends JpaRepository<Users, Integer> {
	
	@Query(value = "select users_no,users_id,users_password,users_name,users_fname,users_phone,users_date from (select rownum n,users_no,users_id,users_password,users_name,users_fname,users_phone,users_date from users) where n between ?1 and ?2", nativeQuery = true)
	public List<Users> selectAll(int start, int end);
	//추가
	@Modifying
	@Query(value = "INSERT into users (users_no, users_id,users_password, users_name, users_fname, users_phone, users_date) values (USERS_SEQ.NEXTVAL, :#{#u.usersId}, :#{#u.usersPassword},:#{#u.usersName}, :#{#u.usersFname}, :#{#u.usersPhone}, :#{#u.usersDate})", nativeQuery = true)
	@Transactional
	void insert(@Param("u") Users u);
	//수정
	@Modifying
	@Query(value = "UPDATE users u set u.users_fname = :usersFname, u.users_phone = :usersPhone where u.users_no = :usersNo", nativeQuery = true)
	@Transactional
	void update(@Param("usersNo") int usersNo, @Param("usersFname") String usersFname, @Param("usersPhone") String usersPhone);
	//삭제
	@Modifying
	@Query(value="delete Users u where u.users_no=?1", nativeQuery = true)
	@Transactional
	public int deleteUsers(int usersNo);
	
	@Query(value="select count(*) from users where users_date = ?1", nativeQuery = true)
	public int countRecord(Date users_date);
	
	//이름으로 검색
	public List<Users> 
	findByUsersNameLike(String usersName);
	
	//아이디로 검색
	public List<Users>
	findByUsersIdLike(String usersId);
}