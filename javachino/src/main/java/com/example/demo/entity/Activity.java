package com.example.demo.entity;

import java.util.List;

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
    @Setter
    @Column(name = "activity_fname1")
    private String activityFname1;
    @Column(name = "activity_fname2")
    private String activityFname2;
    @Column(name = "activity_fname3")
    private String activityFname3;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_no", foreignKey = @ForeignKey(name = "fk_activity_to_business"))
    private Business businessNo;
    
    //activityReview랑 조인해서 쓰기 위해 추가
    @OneToMany(mappedBy = "activity")
    private List<ActivityReview> activityReviews;
} 

