package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(
        name = "accomodation_file",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "ACCOMODATION_FILE_UNIQUE",
                        columnNames = {"users_id", "accomodation_id"}
                )
        }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name="ACCOMODATION_FILE_SEQ_GEN",
        sequenceName = "ACCOMODATION_FILE_SEQ"
)
public class AccomodationFile {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ACCOMODATION_FILE_SEQ_GEN"
    )
    private int accomodation_file_id;
    private String accomodation_file;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accomodation_id", foreignKey = @ForeignKey(name = "fk_accomodation_file_to_accomodation"))
    private Accomodation accomodation_id;
	

  
}
