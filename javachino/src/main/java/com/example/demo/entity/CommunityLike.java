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
@Table(
		name = "community_Like"
		)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name="COMMUNITY_LIKE_SEQ_GEN",
        sequenceName = "COMMUNITY_LIKE_SEQ",
		initialValue = 1,
		allocationSize = 1
) 
public class CommunityLike {
	@Id
	@GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "COMMUNITY_LIKE_SEQ_GEN"
    )
	@Column(name="community_like_no")
	private int communityLikeNo;
	  @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "users_no", foreignKey = @ForeignKey(name = "fk_community_like_to_user"))
	private Users users;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "community_no", foreignKey = @ForeignKey(name = "fk_community_like_to_community"))  
	private Community community;
}
