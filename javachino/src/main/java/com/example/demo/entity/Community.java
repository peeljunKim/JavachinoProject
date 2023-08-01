package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "community")
@SequenceGenerator(
        name="COMMUNITY_SEQ_GEN",
        sequenceName = "COMMUNITY_SEQ"
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Community {
	@Id
	@GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "COMMUNITY_SEQ_GEN"
    )
	private int community_id;
	private Date community_date;
	private String community_title;
	private String community_content;
	private String community_addr;
	private int community_hit;
	@Enumerated(EnumType.ORDINAL)
	private String community_category;
	@Enumerated(EnumType.ORDINAL)
	private int community_select;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", foreignKey = @ForeignKey(name = "fk_community_to_users"))
	private Users users_id
}
