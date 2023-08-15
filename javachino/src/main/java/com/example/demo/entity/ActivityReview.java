package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "activity_review")
@SequenceGenerator(
        name="ACTIVITY_REVIEW_SEQ_GEN",
        sequenceName = "ACTIVITY_REVIEW_SEQ",
    	initialValue = 1,
    	allocationSize = 1
) 
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ActivityReview {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ACTIVITY_REVIEW_SEQ_GEN"
    )
    @Column(name = "activity_review_no")
    private int activityReviewNo;
    @Column(name = "activity_review_star")
    private double activityReviewStar;
    @Column(name = "activity_review_content")
    private String activityReviewContent;
    @LastModifiedDate
    @Temporal(TemporalType.DATE)
    @Column(name = "activity_review_date")
    private LocalDate activityReviewDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_no", foreignKey = @ForeignKey(name = "fk_activity_review_to_users"))
    private Users users;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_no", foreignKey = @ForeignKey(name = "fk_activity_review_to_activity"))
    private Activity activity;
	public int getActivityReviewNo() {
		return activityReviewNo;
	}
	public void setActivityReviewNo(int activityReviewNo) {
		this.activityReviewNo = activityReviewNo;
	}
	public double getActivityReviewStar() {
		return activityReviewStar;
	}
	public void setActivityReviewStar(double activityReviewStar) {
		this.activityReviewStar = activityReviewStar;
	}
	public String getActivityReviewContent() {
		return activityReviewContent;
	}
	public void setActivityReviewContent(String activityReviewContent) {
		this.activityReviewContent = activityReviewContent;
	}
	public LocalDate getActivityReviewDate() {
		return activityReviewDate;
	}
	public void setActivityReviewDate(LocalDate activityReviewDate) {
		this.activityReviewDate = activityReviewDate;
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
