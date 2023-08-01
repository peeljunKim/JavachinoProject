package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(
		name = "communityLike",
		uniqueConstraints = {
				@UniqueConstraint(
						name = "COMMUNITY_LIKE_UNIQUE",
                        columnNames = {"user_id", "community_no"}
                )
        	}
		)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name="COMMUNITY_LIKE_SEQ_GEN",
        sequenceName = "COMMUNITY_LIKE_SEQ"
)
public class CommmunityLike {
	@Id
	@GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "COMMUNITY_LIKE_SEQ_GEN"
    )
	private int community_like_id;
	  @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "users_id", foreignKey = @ForeignKey(name = "fk_community_like_to_user"))
	private Users users_id;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "community_id", foreignKey = @ForeignKey(name = "fk_community_like_to_community"))  
	private Community community_id;
}
