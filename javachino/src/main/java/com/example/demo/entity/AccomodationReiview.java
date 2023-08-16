package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

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
    @Column(name = "accomodation_review_no")
    private int accomodationReviewNo;
    @Column(name = "accomodation_review_star")
    private double accomodationReviewStar;
    @Column(name = "accomodation_review_content")
    private String accomodationReviewContent;
    
    @LastModifiedDate
    @Temporal(TemporalType.DATE)
    @Column(name = "accomodation_review_date")
    private LocalDate accomodationReviewDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_no", foreignKey = @ForeignKey(name = "fk_accom_review_to_users"))
    private Users users;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accomodation_no", foreignKey = @ForeignKey(name = "fk_accom_review_to_accom"))
    private Accomodation accomodation;
	public int getAccomodationReviewNo() {
		return accomodationReviewNo;
	}
	public void setAccomodationReviewNo(int accomodationReviewNo) {
		this.accomodationReviewNo = accomodationReviewNo;
	}
	public double getAccomodationReviewStar() {
		return accomodationReviewStar;
	}
	public void setAccomodationReviewStar(double accomodationReviewStar) {
		this.accomodationReviewStar = accomodationReviewStar;
	}
	public String getAccomodationReviewContent() {
		return accomodationReviewContent;
	}
	public void setAccomodationReviewContent(String accomodationReviewContent) {
		this.accomodationReviewContent = accomodationReviewContent;
	}
	public LocalDate getAccomodationReviewDate() {
		return accomodationReviewDate;
	}
	public void setAccomodationReviewDate(LocalDate accomodationReviewDate) {
		this.accomodationReviewDate = accomodationReviewDate;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public Accomodation getAccomodation() {
		return accomodation;
	}
	public void setAccomodation(Accomodation accomodation) {
		this.accomodation = accomodation;
	}
    

    
    
    
}
