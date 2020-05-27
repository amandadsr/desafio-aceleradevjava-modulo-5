package com.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.challenge.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

	@Query("SELECT DISTINCT cp FROM Company cp "
			+ "INNER JOIN cp.candidates candidate "
			+ "WHERE candidate.id.acceleration.id = ?1")
	List<Company> findByAccelerationId(Long accelerationId);
	
	@Query("SELECT cp FROM Company cp "
			+ "INNER JOIN cp.candidates candidate "
			+ "WHERE candidate.id.user.id = ?1")
	List<Company> findByUserId(Long userId);
}
