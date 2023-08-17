package com.example.demo.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.admin.dao.AdminActivityRvDAO;
import com.example.demo.admin.dto.AdminActivityRvDto;
import com.example.demo.entity.ActivityRv;

import lombok.Setter;

@Service
@Setter
public class AdminActivityRvService {
    @Autowired
    private AdminActivityRvDAO dao;
    
    public List<AdminActivityRvDto> findAll(int start, int end, String cname, String keyword) {
    	List<AdminActivityRvDto> list = null;
    	if(keyword != null && !keyword.equals("")) {			
			switch(cname) {
				case "activityName" : list=dao.findByActivityNameLike("%"+keyword+"%");break;
				case "usersName" : list =  dao.findByUsersNameLike("%"+keyword+"%");break;
			}			
		}else {
			list = dao.getAllActivityRvDto(start, end);
		}
        return list;
    }
    
    //검색된 레코드 수 반환
    public int getTotalRecordByKeyword(String cname,String keyword) {
       int u;
       if(cname.equals("activityName")) {
          u= dao.countByActivityName("%"+keyword+"%");
       }else{
    	   u= dao.countByUsersName("%"+keyword+"%");
       }
       return u;
    }

    public int getActivityRvTotalRecord() {
        return (int) dao.count();
    }
 
    
    public ActivityRv insert(ActivityRv activityRv) {
        return dao.save(activityRv);
    }
    public ActivityRv update(ActivityRv activityRv) {
        return dao.save(activityRv);
    }

    public void delete(int activityRvNo) {
        dao.deleteById(activityRvNo);
    }

}
