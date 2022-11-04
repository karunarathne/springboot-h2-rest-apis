package com.scl.devnest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name = "uuid", unique = true)
	private String uuid;
	
	@Column(name = "email", unique = true, length = 60)
	private String email;
	
	@Column(name = "password", length = 250)
	private String password;
	
	@Column(name = "token", length = 250)
	private String token;
	
	@Column(name = "first_name", length = 30)
	private String firstName;
	
	@Column(name = "last_name", length = 50)
	private String lastName;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "role")
	private String role;
}
