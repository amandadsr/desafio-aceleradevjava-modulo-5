package com.challenge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.challenge.entity.Candidate;
import com.challenge.entity.CandidateId;

public interface CandidateRepository extends JpaRepository<Candidate, CandidateId>{
	
	@Query("SELECT cand FROM Candidate cand "
			+ "WHERE cand.id.user.id = :userId "
			+ "AND cand.id.company.id = :companyId "
			+ "AND cand.id.acceleration.id = :accelerationId")
	Optional<Candidate> findByIdUserCompanyAcceleration(Long userId, Long companyId, Long accelerationId);
	
	@Query("SELECT cand FROM Candidate cand "
			+ "WHERE cand.id.company.id = :companyId")
	List<Candidate> findByCompanyId(@Param("companyId") Long companyId);
	

	@Query("SELECT cand FROM Candidate cand "
			+ "WHERE cand.id.acceleration.id = :accelerationId")
	List<Candidate> findByAccelerationId(@Param("accelerationId") Long accelerationId);
}
