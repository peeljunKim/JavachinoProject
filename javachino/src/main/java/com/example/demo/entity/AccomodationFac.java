package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Data
@Table(name = "accomodation_fac")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccomodationFac {
    @Id
    @OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "accomodation_no")
    private Accomodation accomodation;
    private int spa;
    private int kitchen;
    private int bbq;
    private int pool;
    private int wifi;
    private int parking;
    private int pet;
    private int karaoke;
    
    
   

    

}
