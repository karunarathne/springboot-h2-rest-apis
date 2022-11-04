package com.scl.devnest.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scl.devnest.dto.SearchUserDto;
import com.scl.devnest.dto.UserDto;
import com.scl.devnest.impl.IUserImpl;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	private IUserImpl userImpl;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDto> findById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(userImpl.findById(id));
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ResponseEntity<Page<UserDto>> search(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "role", required = false) String role,
			@RequestParam(value = "status", required = false) String status,
			Pageable pageable
		) {
		return ResponseEntity.ok(userImpl.getPage(
				new SearchUserDto(name, email, role, status), pageable));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> add(@RequestBody UserDto dto) throws Exception{
		userImpl.add(dto);
		return ResponseEntity.created(new URI(null)).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody UserDto dto) throws Exception{
		userImpl.update(dto);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception{
		userImpl.delete(id);
		return ResponseEntity.noContent().build();
	}
}
