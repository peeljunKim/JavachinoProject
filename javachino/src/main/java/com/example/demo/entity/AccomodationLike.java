package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "accomodation_like",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "ACCOMODATION_LIKE_UNIQUE",
                        columnNames = {"users_id", "accomodation_id"}
                )
        }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name="ACCOMODATION_LIKE_SEQ_GEN",
        sequenceName = "ACCOMODATION_LIKE_SEQ"
)
public class AccomodationLike {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ACCOMODATION_LIKE_SEQ_GEN"
    )
    private int accmodation_like_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", foreignKey = @ForeignKey(name = "fk_accomodation_like_to_users"))
    private Users users_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accomodation_id", foreignKey = @ForeignKey(name = "fk_accomodation_like_to_accomodation"))
    private Accomodation accomodation_id;
    

    
}
