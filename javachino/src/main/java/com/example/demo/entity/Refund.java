package com.example.demo.entity;

import java.sql.Date;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "refund")
@SequenceGenerator(
		name = "REFUND_SEQ_GEN",
		sequenceName = "REFUND_SEQ"
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Refund {
	
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "REFUND_SEQ_GEN"
	)
	private int refund_no;
	
	private Date refund_receipt_date;
	private Date refund_finish_date;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pay_no", foreignKey = @ForeignKey(name = "fk_fefund_to_pay"))
	private Pay pay_no;
}
