package com.example.demo.entity;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Table(name = "activity")
@SequenceGenerator(
        name="ACTIVITY_SEQ_GEN",
        sequenceName = "ACTIVITY_SEQ",
    	initialValue = 1,
    	allocationSize = 1
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Activity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ACTIVITY_SEQ_GEN"
    )
    @Column(name = "activity_no")
    private int activityNo;
    @Column(name = "activity_name")
    private String activityName;
    @Column(name = "activity_addr")
    private String activityAddr;
    @Column(name = "activity_explanation")
    private String activityExplanation;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "activity_category")
    private ActivityCategory activityCategory;
    @Column(name = "activity_price")
    private double activityPrice;
    @Column(name = "activity_time")
    private int activityTime;
   
    @Column(name = "activity_fname1")
    private String activityFname1;
    @Column(name = "activity_fname2")
    private String activityFname2;
    @Column(name = "activity_fname3")
    private String activityFname3;
    //테이블 매핑에서는 제외 시켜라!
  	@Transient
  	private MultipartFile uploadFile1;
  	//테이블 매핑에서는 제외 시켜라!
  	@Transient
  	private MultipartFile uploadFile2;
  	//테이블 매핑에서는 제외 시켜라!
  	@Transient
  	private MultipartFile uploadFile3;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_no", foreignKey = @ForeignKey(name = "fk_activity_to_business"))
    private Business businessNo;
    
    //activityReview랑 조인해서 쓰기 위해 추가
    @OneToMany(mappedBy = "activity")
    private List<ActivityReview> activityReviews;

	public int getActivityNo() {
		return activityNo;
	}

	public void setActivityNo(int activityNo) {
		this.activityNo = activityNo;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityAddr() {
		return activityAddr;
	}

	public void setActivityAddr(String activityAddr) {
		this.activityAddr = activityAddr;
	}

	public String getActivityExplanation() {
		return activityExplanation;
	}

	public void setActivityExplanation(String activityExplanation) {
		this.activityExplanation = activityExplanation;
	}

	public ActivityCategory getActivityCategory() {
		return activityCategory;
	}

	public void setActivityCategory(ActivityCategory activityCategory) {
		this.activityCategory = activityCategory;
	}

	public double getActivityPrice() {
		return activityPrice;
	}

	public void setActivityPrice(double activityPrice) {
		this.activityPrice = activityPrice;
	}

	public int getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(int activityTime) {
		this.activityTime = activityTime;
	}

	public String getActivityFname1() {
		return activityFname1;
	}

	public void setActivityFname1(String activityFname1) {
		this.activityFname1 = activityFname1;
	}

	public String getActivityFname2() {
		return activityFname2;
	}

	public void setActivityFname2(String activityFname2) {
		this.activityFname2 = activityFname2;
	}

	public String getActivityFname3() {
		return activityFname3;
	}

	public void setActivityFname3(String activityFname3) {
		this.activityFname3 = activityFname3;
	}

	public Business getBusinessNo() {
		return businessNo;
	}

	public void setBusinessNo(Business businessNo) {
		this.businessNo = businessNo;
	}

	public List<ActivityReview> getActivityReviews() {
		return activityReviews;
	}

	public void setActivityReviews(List<ActivityReview> activityReviews) {
		this.activityReviews = activityReviews;
	}
    
} 

