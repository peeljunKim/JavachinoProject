package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
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
	@Column(name = "community_no")
	private int communityNo;
	@Column(name = "community_date")
	private Date communityDate;
	@Column(name = "community_title")
	private String communityTitle;
	@Column(name = "community_content")
	private String communityContent;
	@Column(name = "community_addr")
	private String communityAddr;
	@Column(name = "community_hit")
	private int communityHit;
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "community_category")
	private CommunityCategory communityCategory;
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "community_select")
	private CommunitySelect communitySelect;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_no", foreignKey = @ForeignKey(name = "fk_community_to_users"))
	private Users users;
	
   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_rv_no", foreignKey = @ForeignKey(name = "fk_community_to_activity_rv"))
   private ActivityRv activityRv;
   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accomodation_rv_no", foreignKey = @ForeignKey(name = "fk_community_to_accomodation_rv"))
   private AccomodationRv accomodationRv;
}
