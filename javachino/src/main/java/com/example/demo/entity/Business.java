package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "business")
@SequenceGenerator(
        name="BUSINESS_SEQ_GEN",
        sequenceName = "BUSINESS_SEQ"
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Business {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "BUSINESS_SEQ_GEN"
    )
    private int business_id;
    private String business_name;
    private String business_addr;
    private String business_phone;
    private String business_manager;
    @Enumerated(EnumType.ORDINAL)
    private BusinessCategory business_category;

}
