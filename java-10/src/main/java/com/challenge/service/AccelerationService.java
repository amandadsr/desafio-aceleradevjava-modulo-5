package com.challenge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.entity.Acceleration;
import com.challenge.repository.AccelerationRepository;
import com.challenge.service.interfaces.AccelerationServiceInterface;

@Service
public class AccelerationService implements AccelerationServiceInterface{

	@Autowired
	private AccelerationRepository accelerationRepository;
	
	@Override
	public Acceleration save(Acceleration object) {
		return accelerationRepository.save(object);
	}

	@Override
	public Optional<Acceleration> findById(Long id) {
		return accelerationRepository.findById(id);
	}

	
	public Optional<Acceleration> findAccelerationByName(String name) {
		return accelerationRepository.findByName(name);
	}
	
	@Override
	public List<Acceleration> findByCompanyId(Long companyId) {
		return accelerationRepository.findByCompanyId(companyId);
	}

}
