package com.example.demo.entity;

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
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "report")
@SequenceGenerator(
        name="REPORT_SEQ_GEN",
        sequenceName = "REPORT_SEQ"
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report {
	@Id
	@GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "REPORT_SEQ_GEN"
    )
	private int report_id;
	@Enumerated(EnumType.ORDINAL)
	private String report_reason;
	@Enumerated(EnumType.ORDINAL)
	private int report_status;
	@Enumerated(EnumType.ORDINAL)
	private int report_category;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_review_id", foreignKey = @ForeignKey(name = "fk_report_to_activity_review"))
	private ActivityReview activityReview_id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pension_review_id", foreignKey = @ForeignKey(name = "fk_report_to_pension_review"))
	private AccomodationReiview pensionReview_id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id", foreignKey = @ForeignKey(name = "fk_report_to_community"))
	private Community community_id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", foreignKey = @ForeignKey(name = "fk_report_to_user"))
	private Users users_id;
}
