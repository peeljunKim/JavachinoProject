package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
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
    private Users usersNo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id", foreignKey = @ForeignKey(name = "fk_activity_rv_to_activity"))
    private Activity activityNo;

}
