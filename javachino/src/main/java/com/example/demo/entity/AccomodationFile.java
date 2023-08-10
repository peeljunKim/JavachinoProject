package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(
        name = "accomodation_file"
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name="ACCOMODATION_FILE_SEQ_GEN",
        sequenceName = "ACCOMODATION_FILE_SEQ",
    	initialValue = 1,
    	allocationSize = 1
)
public class AccomodationFile {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ACCOMODATION_FILE_SEQ_GEN"
    )
    @Column(name = "accomodation_file_no")
    private int accomodationFileNo;
    @Column(name = "accomodation_file_fname1")
    private String accomodationFileFname1;
    @Column(name = "accomodation_file_fname2")
    private String accomodationFileFname2;
    @Column(name = "accomodation_file_fname3")
    private String accomodationFileFname3;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accomodation_no", foreignKey = @ForeignKey(name = "fk_accom_file_to_accom"))
    private Accomodation accomodation;
	

  
}
