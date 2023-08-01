package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "community")
@SequenceGenerator(
        name="COMMUNITY_SEQ_GEN",
        sequenceName = "COMMUNITY_SEQ",
		initialValue = 1,
		allocationSize = 1
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
	private CommunityCategory community_category;
	@Enumerated(EnumType.ORDINAL)
	private CommunitySelect community_select;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_no", foreignKey = @ForeignKey(name = "fk_community_to_users"))
	private Users users_no;
}
