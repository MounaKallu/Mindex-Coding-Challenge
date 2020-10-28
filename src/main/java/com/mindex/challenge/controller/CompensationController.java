package com.mindex.challenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;

@RestController
public class CompensationController {
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private CompensationService compensationService;

	@PostMapping("/createCompensation")
	public Compensation createCompensation(@RequestBody Compensation compensation) {
		LOG.debug("Request to create a Compensation [{}]", compensation);

		return compensationService.createCompensation(compensation);
	}

	@GetMapping("/getCompensation/{id}")
	public Compensation getCompensation(@PathVariable String id) {
		LOG.debug("Reguest to get compensation for id [{}]", id);

		return compensationService.getCompensation(id);
	}
}
