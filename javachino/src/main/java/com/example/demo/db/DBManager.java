package com.example.demo.db;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.AccomodationLikeVO;
import com.example.demo.vo.AccomodationPayVO;
import com.example.demo.vo.ActivityLikeVO;
import com.example.demo.vo.ActivityPayVO;

public class DBManager {
	public static SqlSessionFactory sqlSessionFactory;
	
	   static {
	      try {
	         String resource = "com/example/demo/db/sqlMapConfig.xml";
	         InputStream inputStream = Resources.getResourceAsStream(resource);
	         sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	      }catch (Exception e) {
	         System.out.println("예외발생:"+e.getMessage());
	      }
	   }
	   
	   public static List<AccomodationLikeVO> selectAccomodationLike(int users_no){
		   List<AccomodationLikeVO> list = null;
		   
		   SqlSession session = sqlSessionFactory.openSession();
		   list = session.selectList("accomodationlike.selectAll",users_no);
		   session.close();
		   
		   return list;
	   }
	   
	   public static List<ActivityLikeVO> selectActivityLike(int users_no){
		   List<ActivityLikeVO> list = null;
		   
		   SqlSession session = sqlSessionFactory.openSession();
		   list = session.selectList("activitylike.selectAll",users_no);
		   session.close();
		   
		   return list;
	   }
	   
	   public static List<AccomodationPayVO> selectAccomodationPay(int users_no){
		   List<AccomodationPayVO> list = null;
		   
		   SqlSession session = sqlSessionFactory.openSession();
		   list = session.selectList("accomodationpay.selectAll",users_no);
		   session.close();
		   
		   return list;
	   }
	   
	   public static List<ActivityPayVO> selectActivityPay(int users_no){
		   List<ActivityPayVO> list = null;
		   
		   SqlSession session = sqlSessionFactory.openSession();
		   list = session.selectList("activitypay.selectAll",users_no);
		   session.close();
		   
		   return list;
	   }
}
