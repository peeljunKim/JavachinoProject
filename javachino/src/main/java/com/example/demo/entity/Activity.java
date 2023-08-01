package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "activity")
@SequenceGenerator(
        name="ACTIVITY_SEQ_GEN",
        sequenceName = "ACTIVITY_SEQ",
    	initialValue = 1,
    	allocationSize = 1
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Activity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ACTIVITY_SEQ_GEN"
    )
    private int activity_id;
    private String activity_name;
    private String activity_addr;
    private String activity_explanation;
    @Enumerated(EnumType.ORDINAL)
    private ActivityCategory activity_category;
    private double activity_price;
    private int activity_time;
    @Setter
    private String activity_fname1;
    private String activity_fname2;
    private String activity_fname3;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id", foreignKey = @ForeignKey(name = "fk_activity_to_business"))
    private Business business_id;
}

