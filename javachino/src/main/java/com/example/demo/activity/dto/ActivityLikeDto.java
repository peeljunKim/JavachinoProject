package com.example.demo.activity.dto;

import com.example.demo.entity.ActivityLike;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityLikeDto {
    private int activityLikeNo;
    private UsersDto usersDto;
    private ActivityDto activityDto;

    public ActivityLikeDto(ActivityLike activityLike) {
        this.activityLikeNo = activityLike.getActivityLikeNo();
        this.usersDto = new UsersDto(activityLike.getUsersNo().getUsersNo(),
                                    activityLike.getUsersNo().getUsersId(),
                                    activityLike.getUsersNo().getUsersName(),
                                    activityLike.getUsersNo().getUsersFname(),
                                    activityLike.getUsersNo().getUsersPhone(),
                                    activityLike.getUsersNo().getUsersPassword());
        
        this.activityDto = new ActivityDto(activityLike.getActivityNo());
    }

	public int getActivityLikeNo() {
		return activityLikeNo;
	}

	public void setActivityLikeNo(int activityLikeNo) {
		this.activityLikeNo = activityLikeNo;
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