package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "communityFile")
@SequenceGenerator(
        name="COMMUNITY_FILE_SEQ_GEN",
        sequenceName = "COMMUNITY_FILE_SEQ"
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityFile {
	@Id
	@GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "COMMUNITY_FILE_SEQ_GEN"
    )
	private int community_file_id;
	private String community_file_fname;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id", foreignKey = @ForeignKey(name = "fk_community_file_to_community"))  
	private Community community_id;
}
