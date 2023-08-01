package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "activity_like",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "ACTIVITY_LIKE_UNIQUE",
                        columnNames = {"user_id", "activity_id"}
                )
        }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name="ACTIVITY_LIKE_SEQ_GEN",
        sequenceName = "ACTIVITY_LIKE_SEQ"
)
public class ActivityLike {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ACTIVITY_LIKE_SEQ_GEN"
    )
    private int activity_like_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_no", foreignKey = @ForeignKey(name = "fk_activity_like_to_user"))
    private Users users;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id", foreignKey = @ForeignKey(name = "fk_activity_like_to_activity"))
    private Activity activity;

}
