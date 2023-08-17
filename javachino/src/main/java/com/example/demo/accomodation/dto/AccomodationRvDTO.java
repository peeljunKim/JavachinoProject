package com.example.demo.accomodation.dto;

import java.time.LocalDate;

public class AccomodationRvDTO {

	private int usersNo;
	private int accomodationNo;
	private LocalDate accomodationRvCheckin;
	private LocalDate accomodationRvCheckout;
	private String accomodationRvName;
	private String accomodationRvPeople;
	private String accomodationRvPhone;
	public int getUsersNo() {
		return usersNo;
	}
	public void setUsersNo(int usersNo) {
		this.usersNo = usersNo;
	}
	public int getAccomodationNo() {
		return accomodationNo;
	}
	public void setAccomodationNo(int accomodationNo) {
		this.accomodationNo = accomodationNo;
	}
	public LocalDate getAccomodationRvCheckin() {
		return accomodationRvCheckin;
	}
	public void setAccomodationRvCheckin(LocalDate accomodationRvCheckin) {
		this.accomodationRvCheckin = accomodationRvCheckin;
	}
	public LocalDate getAccomodationRvCheckout() {
		return accomodationRvCheckout;
	}
	public void setAccomodationRvCheckout(LocalDate accomodationRvCheckout) {
		this.accomodationRvCheckout = accomodationRvCheckout;
	}
	public String getAccomodationRvName() {
		return accomodationRvName;
	}
	public void setAccomodationRvName(String accomodationRvName) {
		this.accomodationRvName = accomodationRvName;
	}
	public String getAccomodationRvPeople() {
		return accomodationRvPeople;
	}
	public void setAccomodationRvPeople(String accomodationRvPeople) {
		this.accomodationRvPeople = accomodationRvPeople;
	}
	public String getAccomodationRvPhone() {
		return accomodationRvPhone;
	}
	public void setAccomodationRvPhone(String accomodationRvPhone) {
		this.accomodationRvPhone = accomodationRvPhone;
	}
	public AccomodationRvDTO(int usersNo, int accomodationNo, LocalDate accomodationRvCheckin,
			LocalDate accomodationRvCheckout, String accomodationRvName, String accomodationRvPeople,
			String accomodationRvPhone) {
		super();
		this.usersNo = usersNo;
		this.accomodationNo = accomodationNo;
		this.accomodationRvCheckin = accomodationRvCheckin;
		this.accomodationRvCheckout = accomodationRvCheckout;
		this.accomodationRvName = accomodationRvName;
		this.accomodationRvPeople = accomodationRvPeople;
		this.accomodationRvPhone = accomodationRvPhone;
	}
	public AccomodationRvDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "AccomodationRvDTO [usersNo=" + usersNo + ", accomodationNo=" + accomodationNo
				+ ", accomodationRvCheckin=" + accomodationRvCheckin + ", accomodationRvCheckout="
				+ accomodationRvCheckout + ", accomodationRvName=" + accomodationRvName + ", accomodationRvPeople="
				+ accomodationRvPeople + ", accomodationRvPhone=" + accomodationRvPhone + "]";
	}
	
	
}
