package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "activity_rv")
@SequenceGenerator(
        name="ACTIVITY_RV_SEQ_GEN",
        sequenceName = "ACTIVITY_RV_SEQ",
    	initialValue = 1,
    	allocationSize = 1
         
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ActivityRv {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ACTIVITY_RV_SEQ_GEN"
    )
    @Column(name = "activity_rv_no")
    private int activityRvNo;

    @LastModifiedDate
    @Temporal(TemporalType.DATE)
    @Column(name = "rv_date")
    private LocalDate rvDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "activity_rv_date")
    private LocalDate activityRvDate;
    @Column(name = "activity_rv_people")
    private int activityRvPeople;
    @Column(name = "activity_rv_phone")
    private String activityRvPhone;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_no", foreignKey = @ForeignKey(name = "fk_activity_rv_to_users"))
    private Users users;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_no", foreignKey = @ForeignKey(name = "fk_activity_rv_to_activity"))
    private Activity activity;
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
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
    
}
