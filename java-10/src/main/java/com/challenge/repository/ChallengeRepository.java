package com.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.challenge.entity.Challenge;

public interface ChallengeRepository extends JpaRepository<Challenge, Long>{
	
	@Query("SELECT ch FROM Challenge ch "
			+ "INNER JOIN ch.accelerations acceleration "
			+ "INNER JOIN acceleration.candidates candidate "
			+ "WHERE acceleration.id = ?1 "
			+ "AND candidate.id.user.id = ?2")
	List<Challenge> findByAccelerationIdAndUserId(Long accelerationId, Long userId);
}
