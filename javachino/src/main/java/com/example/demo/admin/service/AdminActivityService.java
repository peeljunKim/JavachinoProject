package com.example.demo.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.admin.repository.AdminActivityRepository;
import com.example.demo.entity.Activity;

import lombok.Setter;

@Setter
@Service
public class AdminActivityService {
    private final AdminActivityRepository adminRepository;

    public AdminActivityService(AdminActivityRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    //테이블 출력
    @Transactional(readOnly = true)
    public Page<Activity> findAll(Pageable pageable) {
        return adminRepository.findAll(pageable);
    }
    //name 검색기능
    @Transactional(readOnly = true)
    public Page<Activity> findByActivityNameContaining(String activityName, Pageable pageable) {
        return adminRepository.findByActivityNameContaining(activityName, pageable);
    }
    //addr 검색기능
    @Transactional(readOnly = true)
    public Page<Activity> findByactivityAddrContaining(String activityAddr, Pageable pageable) {
        return adminRepository.findByActivityAddrContaining(activityAddr, pageable);
    }
    
    @Transactional
    public Activity save(Activity activity) {
        return adminRepository.save(activity);
    }
    //수정기능
    @Transactional
    public Activity update(Integer id, Activity updatedActivity) {
        return adminRepository.findById(id)
                .map(activity -> {
                    activity.setActivityName(updatedActivity.getActivityName());
                    activity.setActivityAddr(updatedActivity.getActivityAddr());
                    activity.setActivityPrice(updatedActivity.getActivityPrice());
                    activity.setActivityTime(updatedActivity.getActivityTime());
                    activity.setActivityExplanation(updatedActivity.getActivityExplanation());
                    activity.setActivityFname1(updatedActivity.getActivityFname1());
                    activity.setActivityFname2(updatedActivity.getActivityFname2());
                    activity.setActivityFname3(updatedActivity.getActivityFname3());
                    
                    return adminRepository.save(activity);
                })
                .orElseThrow(() -> new IllegalArgumentException("Invalid activity Id:" + id));
    }
    //삭제기능
    @Transactional
    public void delete(Integer id) {
        adminRepository.deleteById(id);
    }
    
    
    
    @Transactional(readOnly = true)
    public List<Activity> findAll() {
        return adminRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Optional<Activity> findById(Integer id) {
        return adminRepository.findById(id);
    }

}
