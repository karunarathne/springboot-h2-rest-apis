package com.scl.devnest.service;

import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.scl.devnest.dto.SearchUserDto;
import com.scl.devnest.entity.User;
import com.scl.devnest.repository.UserRepository;
import com.scl.devnest.util.StringUtil;

@Component
public class UserService implements IUserService {
	
	@Autowired
	private UserRepository repository;

	@Override
	public User findById(Long id) {
		Optional<User> user = repository.findById(id);
		return user.isPresent()? user.get() : null;
	}

	@Override
	public User findByEmail(String email) {
		Optional<User> user = repository.findByEmail(email);
		return user.isPresent()? user.get() : null;
	}

	@Override
	public User findByUuid(String uuid) {
		Optional<User> user = repository.findByUuid(uuid);
		return user.isPresent()? user.get() : null;
	}

	@Override
	public Boolean hasUserByEmail(String email) {
		return repository.existsByEmail(email);
	}

	@Override
	public Page<User> getPage(SearchUserDto criteria, Pageable pageable) {
		return repository.findAll(getUserSearchSpecification(criteria), pageable);
	}

	@Override
	public User add(User entity) {
		return repository.save(entity);
	}

	@Override
	public User update(User entity) {
		return repository.save(entity);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	private Specification<User> getUserSearchSpecification(SearchUserDto dto) {
		return new Specification<User>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				Predicate predicate = builder.and();
				
				if(dto != null) {
					if(!StringUtil.isNullOrEmpty(dto.getEmail())) {
						predicate = builder.and(predicate, builder.like(builder.upper(root.get("email")), StringUtil.startsWithText(dto.getEmail().toUpperCase())));
					}
					
					if(!StringUtil.isNullOrEmpty(dto.getName())) {
						predicate = builder.and(predicate, builder.or(
								builder.like(builder.upper(root.get("firstName")), StringUtil.startsWithText(dto.getName().toUpperCase())), 
								builder.like(builder.upper(root.get("lastName")), StringUtil.startsWithText(dto.getName().toUpperCase()))
							)
						);
					}
					
					if(!StringUtil.isNullOrEmpty(dto.getRole())) {
						predicate = builder.and(predicate, builder.like(builder.upper(root.get("role")), StringUtil.startsWithText(dto.getRole().toUpperCase())));
					}
					
					if(!StringUtil.isNullOrEmpty(dto.getStatus())) {
						predicate = builder.and(predicate, builder.like(builder.upper(root.get("status")), StringUtil.startsWithText(dto.getStatus().toUpperCase())));
					}
				}
				
				return predicate;
			}
		};
	}
}
