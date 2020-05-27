package com.challenge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.challenge.entity.Acceleration;

public interface AccelerationRepository extends JpaRepository<Acceleration, Long>{
	
	Optional<Acceleration> findByName(String name);
	
	@Query("SELECT a FROM Acceleration a "
			+ "INNER JOIN a.candidates candidate "
			+ "WHERE candidate.id.company.id = ?1")
	List<Acceleration> findByCompanyId(Long companyId);
	
}
