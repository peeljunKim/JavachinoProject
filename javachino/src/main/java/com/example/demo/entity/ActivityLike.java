package com.example.demo.entity;

import com.example.demo.entity.Activity;
import com.example.demo.entity.ActivityLike;
import com.example.demo.entity.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(
        name = "activity_like"
) 

@SequenceGenerator(
        name="ACTIVITY_LIKE_SEQ_GEN",
        sequenceName = "ACTIVITY_LIKE_SEQ",
    	initialValue = 1,
    	allocationSize = 1
)
public class ActivityLike {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ACTIVITY_LIKE_SEQ_GEN"
    )
    @Column(name = "activity_like_no")
    private int activityLikeNo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_no", foreignKey = @ForeignKey(name = "fk_activity_like_to_users"))
    private Users usersNo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_no", foreignKey = @ForeignKey(name = "fk_activity_like_to_activity"))
    private Activity activityNo;
    
    public ActivityLike(int activityLikeNo, Users usersNo, Activity activityNo) {
        this.activityLikeNo = activityLikeNo;
        this.usersNo = usersNo;
        this.activityNo = activityNo;
    }
    public ActivityLike() {
    }
	public int getActivityLikeNo() {
		return activityLikeNo;
	}
	public void setActivityLikeNo(int activityLikeNo) {
		this.activityLikeNo = activityLikeNo;
	}
	public Users getUsersNo() {
		return usersNo;
	}
	public void setUsersNo(Users usersNo) {
		this.usersNo = usersNo;
	}
	public Activity getActivityNo() {
		return activityNo;
	}
	public void setActivityNo(Activity activityNo) {
		this.activityNo = activityNo;
	}
    
}