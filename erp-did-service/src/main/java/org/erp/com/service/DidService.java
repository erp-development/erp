package org.erp.com.service;

import java.util.List;
import java.util.Optional;

import org.erp.com.dao.DidRepository;
import org.erp.com.entity.Did;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DidService {
	
	@Autowired
	private DidRepository didRepository;

	public List<Did> findAll() {
		return didRepository.findAll();
	}

	public Optional<Did> findById(String id) {
		return didRepository.findById(id);
	}

	public Did save(Did did) {
		return didRepository.save(did);
	}

	public void deleteById(String id) {
		didRepository.deleteById(id);
	}
}