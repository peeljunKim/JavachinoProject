package com.example.demo.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.admin.dao.AdminUsersDAO;
import com.example.demo.entity.Users;

import lombok.Setter;

@Service
@Setter
public class AdminUsersService {
    @Autowired
    private AdminUsersDAO dao;

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
    
    //검색된 레코드 수 반환
    public int getTotalRecordByKeyword(String cname,String keyword) {
       int u;
       if(cname.equals("usersId")) {
          u= dao.countByUsersId("%"+keyword+"%");
       }else{
    	   u= dao.countByUsersName("%"+keyword+"%");
       }
       return u;
    }

    //전체 레코드 수 반환
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
