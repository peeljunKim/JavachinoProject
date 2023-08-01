package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "accomodation_fac")
//@SequenceGenerator(
//        name="ACCOMODATION_FAC_SEQ_GEN",
//        sequenceName = "ACCOMODATION_FAC_SEQ"
//)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccomodationFac {
    @Id
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "ACCOMODATION_FAC_SEQ_GEN"
//    )
    @OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "accomodation_id"/* , foreignKey = @ForeignKey(name = "fk_accomodation_fac_to_accomodation") */)
    private Accomodation accomodation_id;
    private int spa;
    private int kitchen;
    private int bbq;
    private int pool;
    private int wifi;
    private int parking;
    private int pet;
    private int karaoke;
    
    
   

    

}
