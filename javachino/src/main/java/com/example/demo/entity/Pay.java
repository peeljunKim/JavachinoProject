package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pay")
@SequenceGenerator(
		name = "PAY_SEQ_GEN",
		sequenceName = "PAY_SEQ"
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pay {
	
	@Id
	@GeneratedValue(
			strategy =  GenerationType.SEQUENCE,
			generator = "PAY_SEQ_GEN"
	)
	private int pay_no;
	
	private int pay_price;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ldg_rv_no", foreignKey = @ForeignKey(name = "fk_pay_to_accomodation_rv"))
	private int ldg_rv_no;
	
	@ManyToOne(fetch =  FetchType.LAZY)
	@JoinColumn(name = "activity_rv_no", foreignKey = @ForeignKey(name = "fk_pay_to_activity_rv"))
	private int activity_rv_no;
}
