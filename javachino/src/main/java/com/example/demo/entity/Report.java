package com.example.demo.entity;

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
        sequenceName = "REPORT_SEQ",
    	initialValue = 1,
    	allocationSize = 1
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report {
	@Id
	@GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "REPORT_SEQ_GEN"
    )
	@Column(name = "report_no")
	private int reportNo;
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "report_reason")
	private ReportReason reportReason;
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "report_status")
	private ReportStatus reportStatus;
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "report_category")
	private ReportCategory reportCategory;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_no", foreignKey = @ForeignKey(name = "fk_report_to_user"))
	private Users usersNo;
}
