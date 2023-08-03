package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
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
} 

