package com.scl.devnest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.scl.devnest.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User> {

	public abstract boolean existsByEmail(String email);
	
	public abstract Optional<User> findByEmail(String email);
	
	public abstract Optional<User> findByUuid(String uid);
}
