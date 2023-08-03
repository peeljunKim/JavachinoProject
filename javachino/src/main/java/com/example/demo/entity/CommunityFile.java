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
@Table(name = "community_File")
@SequenceGenerator(
        name="COMMUNITY_FILE_SEQ_GEN",
        sequenceName = "COMMUNITY_FILE_SEQ",
		initialValue = 1,
		allocationSize = 1
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityFile {
	@Id
	@GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "COMMUNITY_FILE_SEQ_GEN"
    )
	@Column(name = "community_file_no")
	private int communityFileNo;
	@Column(name = "community_file_fname")
	private String communityFileFname;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_no", foreignKey = @ForeignKey(name = "fk_community_file_to_community"))  
	private Community communityNo;
}
