package com.mindex.challenge.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;

@Service
public class CompensationServiceImpl implements CompensationService {

	private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

	@Autowired
	private CompensationRepository compensationRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Compensation createCompensation(Compensation compensation) {
		LOG.debug("Creating compensation [{}]", compensation);

		return compensationRepository.insert(compensation);

	}

	@Override
	public Compensation getCompensation(String id) {
		LOG.debug("Get compensation for employee id[{}]", id);

		Employee employee = employeeRepository.findByEmployeeId(id);
		if (employee == null) {
			throw new RuntimeException("Invalid employeeId: " + id);
		}

		return compensationRepository.findByEmployee(employee);

	}

}
