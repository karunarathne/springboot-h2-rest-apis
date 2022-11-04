package com.scl.devnest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private Long id;
	private String uuid;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String status;
	private String role;
}
