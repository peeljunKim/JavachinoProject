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

	public int getActivityRvNo() {
		return activityRvNo;
	}

	public void setActivityRvNo(int activityRvNo) {
		this.activityRvNo = activityRvNo;
	}

	public LocalDate getRvDate() {
		return rvDate;
	}

	public void setRvDate(LocalDate rvDate) {
		this.rvDate = rvDate;
	}

	public LocalDate getActivityRvDate() {
		return activityRvDate;
	}

	public void setActivityRvDate(LocalDate activityRvDate) {
		this.activityRvDate = activityRvDate;
	}

	public int getActivityRvPeople() {
		return activityRvPeople;
	}

	public void setActivityRvPeople(int activityRvPeople) {
		this.activityRvPeople = activityRvPeople;
	}

	public String getActivityRvPhone() {
		return activityRvPhone;
	}

	public void setActivityRvPhone(String activityRvPhone) {
		this.activityRvPhone = activityRvPhone;
	}

	public UsersDto getUsersDto() {
		return usersDto;
	}

	public void setUsersDto(UsersDto usersDto) {
		this.usersDto = usersDto;
	}

	public ActivityDto getActivityDto() {
		return activityDto;
	}

	public void setActivityDto(ActivityDto activityDto) {
		this.activityDto = activityDto;
	}
    
    
}