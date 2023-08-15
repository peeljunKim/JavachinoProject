package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "accomodation_info")
@SequenceGenerator(
        name="ACCOMODATION_INFO_SEQ_GEN",
        sequenceName = "ACCOMODATION_INFO_SEQ",
    	initialValue = 1,
    	allocationSize = 1
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccomodationInfo {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ACCOMODATION_INFO_SEQ_GEN"
    )
    @Column(name = "accomodation_info_no")
    private int accomodationInfoNo;
    @Column(name = "accomodation_info_minPerson")
    private int accomodationInfoMinPerson;
    @Column(name = "accomodation_info_maxPersion")
    private int accomodationInfoMaxPersion;
    @Column(name = "accomodation_info_size")
    private String accomodationInfoSize;
    @Column(name = "accomodation_info_explanation")
    private String accomodationInfoExplanation;
    @Column(name = "accomodation_priceper_person")
    private String accomodationPriceperPerson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accomodation_no", foreignKey = @ForeignKey(name = "fk_accom_info_to_accom"))
    private Accomodation accomodation;

	public int getAccomodationInfoNo() {
		return accomodationInfoNo;
	}

	public void setAccomodationInfoNo(int accomodationInfoNo) {
		this.accomodationInfoNo = accomodationInfoNo;
	}

	public int getAccomodationInfoMinPerson() {
		return accomodationInfoMinPerson;
	}

	public void setAccomodationInfoMinPerson(int accomodationInfoMinPerson) {
		this.accomodationInfoMinPerson = accomodationInfoMinPerson;
	}

	public int getAccomodationInfoMaxPersion() {
		return accomodationInfoMaxPersion;
	}

	public void setAccomodationInfoMaxPersion(int accomodationInfoMaxPersion) {
		this.accomodationInfoMaxPersion = accomodationInfoMaxPersion;
	}

	public String getAccomodationInfoSize() {
		return accomodationInfoSize;
	}

	public void setAccomodationInfoSize(String accomodationInfoSize) {
		this.accomodationInfoSize = accomodationInfoSize;
	}

	public String getAccomodationInfoExplanation() {
		return accomodationInfoExplanation;
	}

	public void setAccomodationInfoExplanation(String accomodationInfoExplanation) {
		this.accomodationInfoExplanation = accomodationInfoExplanation;
	}

	public String getAccomodationPriceperPerson() {
		return accomodationPriceperPerson;
	}

	public void setAccomodationPriceperPerson(String accomodationPriceperPerson) {
		this.accomodationPriceperPerson = accomodationPriceperPerson;
	}

	public Accomodation getAccomodation() {
		return accomodation;
	}

	public void setAccomodation(Accomodation accomodation) {
		this.accomodation = accomodation;
	}

    
}
