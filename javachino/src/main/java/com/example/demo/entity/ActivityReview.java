package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "activity_review")
@SequenceGenerator(
        name="ACTIVITY_REVIEW_SEQ_GEN",
        sequenceName = "ACTIVITY_REVIEW_SEQ"
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ActivityReview {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ACTIVITY_REVIEW_SEQ_GEN"
    )
    private int activity_review_id;
    private double activity_review_star;
    private String activity_review_content;
    @LastModifiedDate
    @Temporal(TemporalType.DATE)
    private LocalDate activity_review_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no", foreignKey = @ForeignKey(name = "fk_activity_review_to_user"))
    private Users users;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id", foreignKey = @ForeignKey(name = "fk_activity_review_to_activity"))
    private Activity activity;

}
