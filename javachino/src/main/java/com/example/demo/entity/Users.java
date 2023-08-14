package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
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
    @Column(name = "users_no")
    private int usersNo;
    @Column(name = "users_id")
    private String usersId;
    @Column(name = "users_password")
    private String usersPassword;
    @Column(name = "users_name")
    private String usersName;
    @Column(name = "users_fname")
    private String usersFname;
    @Column(name = "users_phone")
    private String usersPhone;
    @Column(name = "users_date")
    private Date usersDate;
} 
