package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "business")
@SequenceGenerator(
        name="BUSINESS_SEQ_GEN",
        sequenceName = "BUSINESS_SEQ",
    	initialValue = 1,
    	allocationSize = 1
) 
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Business {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "BUSINESS_SEQ_GEN"
    )
    @Column(name = "business_no")
    private int businessNo;
    @Column(name = "business_name")
    private String businessName;
    @Column(name = "business_addr")
    private String businessAddr;
    @Column(name = "business_phone")
    private String businessPhone;
    @Column(name = "business_manager")
    private String businessManager;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "business_category")
    private BusinessCategory businessCategory;

}
