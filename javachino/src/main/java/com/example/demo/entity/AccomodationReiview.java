package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "accomodation_review")
@SequenceGenerator(
        name="ACCOMODATION_REVIEW_SEQ_GEN",
        sequenceName = "ACCOMODATION_REVIEW_SEQ",
    	initialValue = 1,
    	allocationSize = 1
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class AccomodationReiview {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ACCOMODATION_REVIEW_SEQ_GEN"
    )
    private int accomodation_review_id;
    private double accomodation_review_star;
    private String accomodation_review_content;
    @LastModifiedDate
    @Temporal(TemporalType.DATE)
    private LocalDate accomodation_review_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_no", foreignKey = @ForeignKey(name = "fk_accom_review_to_users"))
    private Users users_no;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accomodation_id", foreignKey = @ForeignKey(name = "fk_accom_review_to_accom"))
    private Accomodation accomodation_id;
    


    
    
}
