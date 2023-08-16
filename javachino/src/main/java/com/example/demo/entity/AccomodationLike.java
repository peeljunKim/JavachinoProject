package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "accomodation_like"
)
@SequenceGenerator(
        name="ACCOMODATION_LIKE_SEQ_GEN",
        sequenceName = "ACCOMODATION_LIKE_SEQ",
    	initialValue = 1,
    	allocationSize = 1
) 
public class AccomodationLike {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ACCOMODATION_LIKE_SEQ_GEN"
    )
    @Column(name = "accmodation_like_no")
    
    private int accmodationLikeNo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_no", foreignKey = @ForeignKey(name = "fk_accom_like_to_users"))
    private Users users;
  
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accomodation_no", foreignKey = @ForeignKey(name = "fk_accom_like_to_accom"))
    private Accomodation accomodation;

	public int getAccmodationLikeNo() {
		return accmodationLikeNo;
	}

	public void setAccmodationLikeNo(int accmodationLikeNo) {
		this.accmodationLikeNo = accmodationLikeNo;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Accomodation getAccomodation() {
		return accomodation;
	}

	public void setAccomodation(Accomodation accomodation) {
		this.accomodation = accomodation;
	}

	public AccomodationLike() {
	}

    

    
}
