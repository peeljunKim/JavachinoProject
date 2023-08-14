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
    private char spa;
    private char kitchen;
    private char bbq;
    private char pool;
    private char wifi;
    private char parking;
    private char pet;
    private char karaoke;
    
    
   

    

}
