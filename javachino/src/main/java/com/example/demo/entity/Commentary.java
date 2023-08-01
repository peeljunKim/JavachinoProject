package com.example.demo.entity;

import java.util.Date;

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
@Table(name = "commentary")
@SequenceGenerator(
        name="COMMENTARY_SEQ_GEN",
        sequenceName = "COMMENTARY_SEQ",
		initialValue = 1,
		allocationSize = 1
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Commentary {
	@Id
	@GeneratedValue(
            strategy=GenerationType.SEQUENCE,
            		generator = "COMMENTARY_SEQ"
	)
	private int commentary_id;
	private Date commentary_date;
	private String commentary_content;
	  @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "users_no", foreignKey = @ForeignKey(name = "fk_commentary_to_user"))
	private Users users_no;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "community_id", foreignKey = @ForeignKey(name = "fk_commentary_to_community"))  
	private Community community_id;
}
