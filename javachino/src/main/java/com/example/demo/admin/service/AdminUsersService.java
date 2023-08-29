package com.example.demo.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.admin.repository.AdminUsersRepository;
import com.example.demo.entity.Users;

import lombok.Setter;

@Setter
@Service
public class AdminUsersService {
    private final AdminUsersRepository adminRepository;

    public AdminUsersService(AdminUsersRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    //테이블 출력
    @Transactional(readOnly = true)
    public Page<Users> findAll(Pageable pageable) {
        return adminRepository.findAll(pageable);
    }
    //name 검색기능
    @Transactional(readOnly = true)
    public Page<Users> findByUsersIdContaining(String usersId, Pageable pageable) {
        return adminRepository.findByUsersIdContaining(usersId, pageable);
    }
    //addr 검색기능
    @Transactional(readOnly = true)
    public Page<Users> findByUsersNameContaining(String usersName, Pageable pageable) {
        return adminRepository.findByUsersNameContaining(usersName, pageable);
    }
    
    @Transactional
    public Users save(Users users) {
        return adminRepository.save(users);
    }
    //수정기능
    @Transactional
    public Users update(Integer id, Users updatedUsers) {
        return adminRepository.findById(id)
                .map(users -> {
                	users.setUsersName(updatedUsers.getUsersName());
                	users.setUsersFname(updatedUsers.getUsersFname());
                    
                    return adminRepository.save(users);
                })
                .orElseThrow(() -> new IllegalArgumentException("Invalid users Id:" + id));
    }
    //삭제기능
    @Transactional
    public void delete(Integer id) {
        adminRepository.deleteById(id);
    }
    //전체 레코드 수 반환
    public int getTotalRecord() {
        return (int) adminRepository.count();
    }
    
    @Transactional(readOnly = true)
    public List<Users> findAll() {
        return adminRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Optional<Users> findById(Integer id) {
        return adminRepository.findById(id);
    }

}
