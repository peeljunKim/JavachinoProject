package com.example.demo.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.admin.dao.AdminBusinessDAO;
import com.example.demo.entity.Business;

import lombok.Setter;

@Service
@Setter
public class AdminBusinessService {
	@Autowired
	private AdminBusinessDAO dao;
	
	public List<Business> findAll(int start, int end, String cname, String keyword) {
    	List<Business> list = null;
    	if(keyword != null && !keyword.equals("")) {			
			switch(cname) {
				case "businessName": list=dao.findByBusinessNameLike("%"+keyword+"%");break;
				case "businessAddr":list =  dao.findByBusinessAddrLike("%"+keyword+"%");break;
				case "businessPhone":list =  dao.findByBusinessPhoneLike("%"+keyword+"%");break;
				case "businessManager":list =  dao.findByBusinessManagerLike("%"+keyword+"%");break;
			}			
		}else {
			list = dao.selectAll(start, end);
		}
        return list;
    }
    
    //검색된 레코드 수 반환
    public int getTotalRecordByKeyword(String cname,String keyword) {
       int b=0;
       if(cname.equals("businessName")) {
          b= dao.countByBusinessName("%"+keyword+"%");
       }else if(cname.equals("businessAddr")){
    	   b= dao.countByBusinessAddr("%"+keyword+"%");
       }else if(cname.equals("businessPhone")){
    	   b= dao.countByBusinessPhone("%"+keyword+"%");
       }else if(cname.equals("businessManager")){
    	   b= dao.countByBusinessManager("%"+keyword+"%");
       }
       return b;
    }
    //전체 레코드 수 반환
    public int getTotalRecord() {
        return (int) dao.count();
    }
    //추가
    public void insert(Business b) {
        dao.insert(b);
    }
    //수정
    public void update(int businessNo, String businessName, String businessAddr, String businessPhone, String businessManager) {
		dao.update(businessNo, businessName, businessAddr, businessPhone, businessManager);
	}
    //삭제
    public int deleteBusiness(int businessNo) {
		return dao.deleteBusiness(businessNo);
	}
	
}
