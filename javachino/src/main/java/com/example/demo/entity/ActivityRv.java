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
        sequenceName = "ACTIVITY_RV_SEQ"
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ActivityRv {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ACTIVITY_RV_SEQ_GEN"
    )
    private int activity_rv_id;

    @LastModifiedDate
    @Temporal(TemporalType.DATE)
    private LocalDate rv_date;

    @Temporal(TemporalType.DATE)
    private LocalDate activity_rv_date;

    private int activity_rv_people;
    private String activity_rv_Phone;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_no", foreignKey = @ForeignKey(name = "fk_activity_rv_to_user"))
    private Users users;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id", foreignKey = @ForeignKey(name = "fk_activity_rv_to_activity"))
    private Activity activity;

}
