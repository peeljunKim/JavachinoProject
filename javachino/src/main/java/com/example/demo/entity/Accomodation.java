package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "accomodation")
@SequenceGenerator(
		name = "ACCOMODATION_SEQ_GEN", 
		sequenceName = "ACCOMODATION_SEQ",
initialValue = 1,
allocationSize = 1)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Accomodation {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOMODATION_SEQ_GEN")
	private int accomodation_id;
	private String accomodation_name;
	private String accomodation_addr;
	private String accomodation_phone;
	@Enumerated(EnumType.ORDINAL)
	private AccomodationCategory accomodation_category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "business_id", foreignKey = @ForeignKey(name = "fk_accom_to_business"))
	private Business business_id;

	public Accomodation(int accomodation_id, String accomodation_name, String accomodation_addr,
			String accomodation_phone, AccomodationCategory accomodation_category, Business business_id) {
		super();
		this.accomodation_id = accomodation_id;
		this.accomodation_name = accomodation_name;
		this.accomodation_addr = accomodation_addr;
		this.accomodation_phone = accomodation_phone;
		this.accomodation_category = accomodation_category;
		this.business_id = business_id;
	}


}
