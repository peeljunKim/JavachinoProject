package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "commentary")
@SequenceGenerator(
        name="COMMENTARY_SEQ_GEN",
        sequenceName = "COMMENTARY_SEQ"
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
	    @JoinColumn(name = "users_id", foreignKey = @ForeignKey(name = "fk_commentary_to_user"))
	private Users users_id;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "community_id", foreignKey = @ForeignKey(name = "fk_commentary_to_community"))  
	private Community community_id;
}
