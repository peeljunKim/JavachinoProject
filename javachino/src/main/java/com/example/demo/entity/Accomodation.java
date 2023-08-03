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
	@Column(name = "accomodation_no")
	private int accomodationNo;
	@Column(name = "accomodation_name")
	private String accomodationName;
	@Column(name = "accomodation_addr")
	private String accomodationAddr;
	@Column(name = "accomodation_price")
	private String accomodationPrice;
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "accomodation_category")
	private AccomodationCategory accomodationCategory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "business_no", foreignKey = @ForeignKey(name = "fk_accom_to_business"))
	private Business businessNo;




}
