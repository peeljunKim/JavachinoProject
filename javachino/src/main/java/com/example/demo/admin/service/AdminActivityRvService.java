package com.example.demo.admin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.admin.repository.AdminActivityRvRepository;
import com.example.demo.entity.ActivityRv;

import lombok.Setter;

@Setter
@Service
public class AdminActivityRvService {
    private final AdminActivityRvRepository activityRvRepository;

    public AdminActivityRvService(AdminActivityRvRepository activityRvRepository) {
        this.activityRvRepository = activityRvRepository;
    }
    //테이블 출력
    @Transactional(readOnly = true)
    public Page<ActivityRv> findAll(Pageable pageable) {
        return activityRvRepository.findAll(pageable);
    }
    
    //name 검색기능
    @Transactional(readOnly = true)
    public Page<ActivityRv> findByUsers_UsersNameContaining(String UsersName, Pageable pageable) {
        return activityRvRepository.findByUsers_UsersNameContaining(UsersName, pageable);
    }
    
    //name 검색기능
    @Transactional(readOnly = true)
    public Page<ActivityRv> findByActivity_ActivityNameContaining(String activityName, Pageable pageable) {
        return activityRvRepository.findByActivity_ActivityNameContaining(activityName, pageable);
    }

    
    @Transactional
    public ActivityRv save(ActivityRv activityRv) {
        return activityRvRepository.save(activityRv);
    }
    //수정기능
    @Transactional
    public ActivityRv update(Integer id, ActivityRv updatedActivityRv) {
        return activityRvRepository.findById(id)
                .map(activityRv -> {
                	activityRv.setActivityRvNo(updatedActivityRv.getActivityRvNo());
                	activityRv.setRvDate(updatedActivityRv.getRvDate());
                	activityRv.setActivityRvDate(updatedActivityRv.getActivityRvDate());
                	activityRv.setActivityRvPeople(updatedActivityRv.getActivityRvPeople());
                	activityRv.setActivityRvPhone(updatedActivityRv.getActivityRvPhone());
                    
                    return activityRvRepository.save(activityRv);
                })
                .orElseThrow(() -> new IllegalArgumentException("Invalid activityRv Id:" + id));
    }
    //삭제기능
    @Transactional
    public void delete(Integer id) {
    	activityRvRepository.deleteById(id);
    }
    
    public int getActivityRvTotalRecord() {
        return (int) activityRvRepository.count();
    }
}
