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
    private int accomodation_info_id;
    private String accomodation_info_price;
    private String accomodation_info_minPerson;
    private String accomodation_info_maxPersion;
    private String accomodation_info_size;
    private String accomodation_info_explanation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accmodation_id", foreignKey = @ForeignKey(name = "fk_accom_info_to_accom"))
    private Accomodation accomodation_id;

	

}
