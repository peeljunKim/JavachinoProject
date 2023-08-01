package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@SequenceGenerator(
        name="USERS_SEQ_GEN",
        sequenceName = "USERS_SEQ",
    	initialValue = 1,
    	allocationSize = 1
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users {
    @Id
    @GeneratedValue(
		strategy =  GenerationType.SEQUENCE,
		generator = "USERS_SEQ_GEN"
    )
    private int users_no;
    private String users_id;
    private String users_name;
    private String users_fname;
    private String users_phone;
}
