package com.challenge.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.challenge.entity.Submission;
import com.challenge.entity.SubmissionId;

public interface SubmissionRepository extends JpaRepository<Submission, SubmissionId>{

	@Query("SELECT MAX(sub.score) "
			+ "FROM Submission sub "
			+ "WHERE sub.id.challenge.id = ?1")
	Optional<BigDecimal> findHigherScoreByChallengeId(Long challengeId);
	
	@Query("SELECT sub FROM Submission sub "
			+ "INNER JOIN sub.id.challenge challenge "
			+ "INNER JOIN sub.id.challenge.accelerations acceleration "
			+ "WHERE challenge.id = ?1 "
			+ "AND acceleration.id = ?2")
	List<Submission> findByChallengeIdAndAccelerationId(Long challengeId, Long accelerationId);
	
}
