package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "accomodation_rv")
@SequenceGenerator(
        name="ACCOMODATION_RV_SEQ_GEN",
        sequenceName = "ACCOMODATION_RV_SEQ",
    	initialValue = 1,
    	allocationSize = 1
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class AccomodationRV {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ACCOMODATION_RV_SEQ_GEN"
    )
    @Column(name = "ACCOMODATION_RV_NO")
    private int  accomodationRVNo;
    @Column(name = "accomodation_rv_checkin")
    private String accomodationRVCheckIn;
    @Column(name = "accomodation_rv_checkout")
    private String accomodationRVCheckOut;
    
    @LastModifiedDate
    @Temporal(TemporalType.DATE)
    @Column(name = "accomodation_rv_date")
    private LocalDate accomodationRVDate;
    @Column(name = "accomodation_rv_Name")
    private String accomodationRVName;
    @Column(name = "accomodation_rv_People")
    private String accomodationRVPeople;
    @Column(name = "accomodation_RV_Phone")
    private String accomodationRVPhone;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_no", foreignKey = @ForeignKey(name = "fk_accom_rv_to_users"))
    private Users users;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accomodation_no", foreignKey = @ForeignKey(name = "fk_accom_rv_to_accom"))
    private Accomodation accomodation;
	public int getAccomodationRVNo() {
		return accomodationRVNo;
	}
	public void setAccomodationRVNo(int accomodationRVNo) {
		this.accomodationRVNo = accomodationRVNo;
	}
	public String getAccomodationRVCheckIn() {
		return accomodationRVCheckIn;
	}
	public void setAccomodationRVCheckIn(String accomodationRVCheckIn) {
		this.accomodationRVCheckIn = accomodationRVCheckIn;
	}
	public String getAccomodationRVCheckOut() {
		return accomodationRVCheckOut;
	}
	public void setAccomodationRVCheckOut(String accomodationRVCheckOut) {
		this.accomodationRVCheckOut = accomodationRVCheckOut;
	}
	public LocalDate getAccomodationRVDate() {
		return accomodationRVDate;
	}
	public void setAccomodationRVDate(LocalDate accomodationRVDate) {
		this.accomodationRVDate = accomodationRVDate;
	}
	public String getAccomodationRVName() {
		return accomodationRVName;
	}
	public void setAccomodationRVName(String accomodationRVName) {
		this.accomodationRVName = accomodationRVName;
	}
	public String getAccomodationRVPeople() {
		return accomodationRVPeople;
	}
	public void setAccomodationRVPeople(String accomodationRVPeople) {
		this.accomodationRVPeople = accomodationRVPeople;
	}
	public String getAccomodationRVPhone() {
		return accomodationRVPhone;
	}
	public void setAccomodationRVPhone(String accomodationRVPhone) {
		this.accomodationRVPhone = accomodationRVPhone;
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
