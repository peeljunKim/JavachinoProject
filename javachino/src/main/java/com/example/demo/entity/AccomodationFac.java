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
	public Accomodation getAccomodation() {
		return accomodation;
	}
	public void setAccomodation(Accomodation accomodation) {
		this.accomodation = accomodation;
	}
	public char getSpa() {
		return spa;
	}
	public void setSpa(char spa) {
		this.spa = spa;
	}
	public char getKitchen() {
		return kitchen;
	}
	public void setKitchen(char kitchen) {
		this.kitchen = kitchen;
	}
	public char getBbq() {
		return bbq;
	}
	public void setBbq(char bbq) {
		this.bbq = bbq;
	}
	public char getPool() {
		return pool;
	}
	public void setPool(char pool) {
		this.pool = pool;
	}
	public char getWifi() {
		return wifi;
	}
	public void setWifi(char wifi) {
		this.wifi = wifi;
	}
	public char getParking() {
		return parking;
	}
	public void setParking(char parking) {
		this.parking = parking;
	}
	public char getPet() {
		return pet;
	}
	public void setPet(char pet) {
		this.pet = pet;
	}
	public char getKaraoke() {
		return karaoke;
	}
	public void setKaraoke(char karaoke) {
		this.karaoke = karaoke;
	}
    
    
   

    

}
