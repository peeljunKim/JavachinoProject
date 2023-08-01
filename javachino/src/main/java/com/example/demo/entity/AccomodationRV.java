package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "accomodation_rv")
@SequenceGenerator(
        name="ACCMOTATION_RV_SEQ_GEN",
        sequenceName = "ACCMOTATION_RV_SEQ"
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class AccomodationRV {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ACCMOTATION_RV_SEQ_GEN"
    )
    private int  accomodation_rv_id;
    private String accomodation_rv_checkIn;
    private String accomodation_rv_checkOut;
    private String accomodation_rv_dateRV;
    private String accomodation_rv_nameRV;
    private String accomodation_rv_numberOfPerson;
    private String accomodation_rv_phoneRV;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", foreignKey = @ForeignKey(name = "fk_accomodation_rv_to_users"))
    private Users users;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accomodation_id", foreignKey = @ForeignKey(name = "fk_accomodation_rv_to_accomodation"))
    private Accomodation accomodation;


    
    
}
