package com.example.demo.mypage.service;

import java.util.Optional;

import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Users;
import com.example.demo.login.MyUsersDAO;

import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import lombok.Setter;

@Service
@Setter
public class MyUsersService {
	@Autowired
	private MyUsersDAO dao;
	
	//pk에 해당하는 레코드 하나를 검색
	public Users getOne(String usersId) {
	    Optional<Users> userOptional = dao.findByUsersId(usersId);
	    return userOptional.orElse(null);
	}
	
    public Optional<Users> getUserByUsersId(String usersId) {
        return dao.findByUsersId(usersId);
    }
    
    public Users getUserById(String usersId) {
        return dao.findByUsersId(usersId).orElse(null);
    }

    @Transactional
    public void updateUserProfile(String usersId, Users updatedUser, MultipartFile profileImage) {
        Optional<Users> userOptional = dao.findByUsersId(usersId);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            user.setUsersName(updatedUser.getUsersName());
            user.setUsersPhone(updatedUser.getUsersPhone());
            // 프로필 이미지 업로드 처리
            if (!profileImage.isEmpty()) {
                String originalFilename = profileImage.getOriginalFilename();
                user.setUsersFname(originalFilename);
            }
            System.out.println("UserUpdate Service");
            dao.save(user); // 정보 저장
        }
    }
    
    public void deleteUserProfile(String usersId) {
        dao.deleteByUsersId(usersId);
    }

}
