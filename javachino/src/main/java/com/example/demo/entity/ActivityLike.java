package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(
        name = "activity_like"
) 
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name="ACTIVITY_LIKE_SEQ_GEN",
        sequenceName = "ACTIVITY_LIKE_SEQ",
    	initialValue = 1,
    	allocationSize = 1
)
public class ActivityLike {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ACTIVITY_LIKE_SEQ_GEN"
    )
    @Column(name = "activity_like_no")
    private int activityLikeNo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_no", foreignKey = @ForeignKey(name = "fk_activity_like_to_users"))
    private Users users;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_no", foreignKey = @ForeignKey(name = "fk_activity_like_to_activity"))
    private Activity activity;

}
