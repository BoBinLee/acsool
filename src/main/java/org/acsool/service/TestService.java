package org.acsool.service;

import org.acsool.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
	@Autowired
	TestRepository repository;

	public String test() {
		return repository.findAll().get(1).getName();
	}
}
