package com.example.demo.activity.dto;

import java.time.LocalDate;

import com.example.demo.entity.ActivityRv;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityRvDto {
    private int activityRvNo;
    private LocalDate rvDate; // 결제한 날짜
    private LocalDate activityRvDate; // 액티비티 진행 날짜
    private int activityRvPeople;
    private String activityRvPhone;
    private UsersDto usersDto;
    private ActivityDto activityDto;

    public ActivityRvDto(ActivityRv activityRv) {
        this.activityRvNo = activityRv.getActivityRvNo();
        this.rvDate = activityRv.getRvDate();
        this.activityRvDate = activityRv.getActivityRvDate();
        this.activityRvPeople = activityRv.getActivityRvPeople();
        this.activityRvPhone = activityRv.getActivityRvPhone();
        
        // UsersDto 생성
        this.usersDto = new UsersDto(activityRv.getUsers().getUsersNo(),
                activityRv.getUsers().getUsersId(),
                activityRv.getUsers().getUsersName(),
                activityRv.getUsers().getUsersFname(),
                activityRv.getUsers().getUsersPhone(),
                activityRv.getUsers().getUsersPassword()); // Include password here

        // ActivityDto 생성
        if (activityRv.getActivity() != null) {
            this.activityDto = new ActivityDto(activityRv.getActivity());
        }
    }
}