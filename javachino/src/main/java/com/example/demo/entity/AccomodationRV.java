package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Data
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
    @Column(name = "accomodation_rv_no")
    private int  accomodationRVNo;
    @Temporal(TemporalType.DATE)
    @Column(name = "accomodation_rv_checkIn")
    private LocalDate accomodationRVCheckIn;
    @Temporal(TemporalType.DATE)
    @Column(name = "accomodation_rv_checkOut")
    private LocalDate accomodationRVCheckOut;
    
    @LastModifiedDate
    @Temporal(TemporalType.DATE)
    @Column(name = "accomodation_rv_date")
    private LocalDate accomodationRVDate;
    @Column(name = "accomodation_rv_Name")
    private String accomodationRVName;
    @Column(name = "accomodation_rv_numberOfPerson")
    private String accomodationRVNumberOfPerson;
    @Column(name = "accomodation_RV_Phone")
    private String accomodationRVPhone;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_no", foreignKey = @ForeignKey(name = "fk_accom_rv_to_users"))
    private Users usersNo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accomodation_no", foreignKey = @ForeignKey(name = "fk_accom_rv_to_accom"))
    private Accomodation accomodationNo;


    
    
}
