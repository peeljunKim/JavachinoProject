package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
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

	public int getAccomodationFileNo() {
		return accomodationFileNo;
	}

	public void setAccomodationFileNo(int accomodationFileNo) {
		this.accomodationFileNo = accomodationFileNo;
	}

	public String getAccomodationFileFname1() {
		return accomodationFileFname1;
	}

	public void setAccomodationFileFname1(String accomodationFileFname1) {
		this.accomodationFileFname1 = accomodationFileFname1;
	}

	public String getAccomodationFileFname2() {
		return accomodationFileFname2;
	}

	public void setAccomodationFileFname2(String accomodationFileFname2) {
		this.accomodationFileFname2 = accomodationFileFname2;
	}

	public String getAccomodationFileFname3() {
		return accomodationFileFname3;
	}

	public void setAccomodationFileFname3(String accomodationFileFname3) {
		this.accomodationFileFname3 = accomodationFileFname3;
	}

	public Accomodation getAccomodation() {
		return accomodation;
	}

	public void setAccomodation(Accomodation accomodation) {
		this.accomodation = accomodation;
	}
	
    
  
}
