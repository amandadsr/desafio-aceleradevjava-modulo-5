package com.challenge.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.entity.Submission;
import com.challenge.repository.SubmissionRepository;
import com.challenge.service.interfaces.SubmissionServiceInterface;

@Service
public class SubmissionService implements SubmissionServiceInterface {

	@Autowired
	SubmissionRepository submissionRepository;

	@Override
	public Submission save(Submission object) {
		return submissionRepository.save(object);
	}

	@Override
	public BigDecimal findHigherScoreByChallengeId(Long challengeId) {
		Optional<BigDecimal> score = submissionRepository.findHigherScoreByChallengeId(challengeId);
		if (score.equals(null)) {
			return BigDecimal.ZERO;
		}
		return score.get();
	}

	@Override
	public List<Submission> findByChallengeIdAndAccelerationId(Long challengeId, Long accelerationId) {
		return submissionRepository.findByChallengeIdAndAccelerationId(challengeId, accelerationId);
	}

}
