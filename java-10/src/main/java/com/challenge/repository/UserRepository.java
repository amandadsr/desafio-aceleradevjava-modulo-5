package com.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.challenge.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	@Query("SELECT u FROM User u "
			+ "INNER JOIN u.candidates candidate "
			+ "WHERE candidate.id.acceleration.name = ?1")
	List<User> findByAccelerationName(String name);
	
	@Query("SELECT u FROM User u "
			+ "INNER JOIN u.candidates candidate "
			+ "WHERE candidate.id.company.id = ?1")
	List<User> findByCompanyId(Long companyId);
	
}
