package com.example.demo.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.admin.dao.UsersDAO;
import com.example.demo.entity.Users;

import lombok.Setter;

@Service
@Setter
public class UsersService {
    @Autowired
    private UsersDAO dao;

    public List<Users> findAll(int start, int end, String cname, String keyword) {
    	List<Users> list = null;
    	if(keyword != null && !keyword.equals("")) {			
			switch(cname) {
				case "usersId": list=dao.findByUsersIdLike("%"+keyword+"%");break;
				case "usersName":list =  dao.findByUsersNameLike("%"+keyword+"%");break;
			}			
		}else {
			list = dao.selectAll(start, end);
		}
        return list;
    }
    
    public int getTotalRecord() {
        return (int) dao.count();
    }
    //추가
    public void insert(Users u) {
        dao.insert(u);
    }
    //수정
    public void update(int usersNo, String usersFname, String usersPhone) {
		dao.update(usersNo, usersFname, usersPhone);
	}
    //삭제
    public int deleteUsers(int usersNo) {
		return dao.deleteUsers(usersNo);
	}
}
