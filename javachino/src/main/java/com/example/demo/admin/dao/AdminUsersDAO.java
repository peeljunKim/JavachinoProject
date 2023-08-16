package com.example.demo.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Users;

import jakarta.transaction.Transactional;

@Repository
public interface AdminUsersDAO extends JpaRepository<Users, Integer> {
	//페이징
	@Query(value = "select users_no,users_id,users_password,users_name,users_fname,users_phone,users_date from (select rownum n,users_no,users_id,users_password,users_name,users_fname,users_phone,users_date from users) where n between ?1 and ?2 order by users_no", nativeQuery = true)
	public List<Users> selectAll(int start, int end);
	
	//이름으로 검색
	public List<Users> 
	findByUsersNameLike(String usersName);
	
	//아이디로 검색
	public List<Users>
	findByUsersIdLike(String usersId);
	
	
	@Query(value ="select count(*) from Users u where users_id like ?1", nativeQuery = true)
	public int countByUsersId(String keyword);
	   
	@Query(value ="select count(*) from Users u where users_name like ?1", nativeQuery = true)
	public int countByUsersName(String keyword);
	
	
	
	
	//추가
	@Modifying
	@Query(value = "INSERT into users (users_no, users_id,users_password, users_name, users_fname, users_phone, users_date) values (USERS_SEQ.NEXTVAL, :#{#u.usersId}, :#{#u.usersPassword},:#{#u.usersName}, :#{#u.usersFname}, :#{#u.usersPhone}, CURRENT_DATE)", nativeQuery = true)
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

}