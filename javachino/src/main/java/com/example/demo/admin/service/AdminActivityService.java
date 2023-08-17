package com.example.demo.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.admin.dao.AdminActivityDAO;
import com.example.demo.admin.dto.AdminActivityDto;
import com.example.demo.entity.Activity;

import lombok.Setter;

@Service
@Setter
public class AdminActivityService {
    @Autowired
    private AdminActivityDAO dao;
    
    public List<AdminActivityDto> findAll(int start, int end, String cname, String keyword) {
        List<AdminActivityDto> list = null;
        if (keyword != null && !keyword.equals("")) {
            switch (cname) {
                case "activityName":
                    list = dao.findByActivityNameLike("%" + keyword + "%");break;
                case "activityAddr":
                    list = dao.findByActivityAddrLike("%" + keyword + "%");break;
            }
        } else {
            list = dao.selectPartialData(start, end);
        }
        return list;
    }
    
    //검색된 레코드 수 반환
    public int getTotalRecordByKeyword(String cname,String keyword) {
       int a;
       if(cname.equals("activityName")) {
          a= dao.countByActivityName("%"+keyword+"%");
       }else{
    	   a= dao.countByActivityAddr("%"+keyword+"%");
       }
       return a;
    }

    //전체 레코드 수 반환
    public int getTotalRecord() {
        return (int) dao.count();
    }
    
    //추가
    public void insert(Activity a) {
        dao.insert(a);
    }
    //수정
    public void update(int activityNo, String activityName, String activityAddr, String activityExplanation, String activityPrice, String activityTime, String activityFname1, String activityFname2, String activityFname3) {
		dao.update(activityNo, activityName, activityAddr, activityExplanation, activityPrice, activityTime, activityFname1, activityFname2, activityFname3);
	}
    //삭제
    public int deleteActivity(int activityNo) {
		return dao.deleteActivity(activityNo);
	}
    
}
