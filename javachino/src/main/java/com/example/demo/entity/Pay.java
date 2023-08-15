package com.example.demo.entity;

import jakarta.persistence.Column;
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
@Data
@Table(name = "pay")
@SequenceGenerator(
		name = "PAY_SEQ_GEN",
		sequenceName = "PAY_SEQ",
		initialValue = 1,
		allocationSize = 1
) 
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pay {
	
	@Id
	@GeneratedValue(
			strategy =  GenerationType.SEQUENCE,
			generator = "PAY_SEQ_GEN"
	)
	@Column(name = "pay_no")
	private int payNo;
	@Column(name = "pay_price")
	private int payPrice;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "accomodation_rv_no", foreignKey = @ForeignKey(name = "fk_pay_to_accomodation_rv"))
	private  AccomodationRV accomodationRV;
	
	@ManyToOne(fetch =  FetchType.LAZY)
	@JoinColumn(name = "activity_rv_no", foreignKey = @ForeignKey(name = "fk_pay_to_activity_rv"))
	private ActivityRv activityRv;

	public int getPayNo() {
		return payNo;
	}

	public void setPayNo(int payNo) {
		this.payNo = payNo;
	}

	public int getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(int payPrice) {
		this.payPrice = payPrice;
	}

	public AccomodationRV getAccomodationRV() {
		return accomodationRV;
	}

	public void setAccomodationRV(AccomodationRV accomodationRV) {
		this.accomodationRV = accomodationRV;
	}

	public ActivityRv getActivityRv() {
		return activityRv;
	}

	public void setActivityRv(ActivityRv activityRv) {
		this.activityRv = activityRv;
	}
	
}
