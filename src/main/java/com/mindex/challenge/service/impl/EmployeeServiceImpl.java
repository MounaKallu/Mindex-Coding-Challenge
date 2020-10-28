package com.mindex.challenge.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }

    @Override
    public ReportingStructure getReportingStructure(String id) {
		LOG.debug("Reporting Structure of id  [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        ReportingStructure rs = new ReportingStructure();
        rs.setEmployee(employee);
		// Using getDirectReports() to get the number of direct reports associated with the id.
        rs.setNumberOfReports(getDirectReports(employee.getEmployeeId()));
        return rs;
    }

    Integer getDirectReports(String id) {
        Employee employee = employeeRepository.findByEmployeeId(id);
        if (null == employee.getDirectReports()) {
            return 0;
        }
        Integer directReps = employee.getDirectReports().size();
        for (int i = 0; i < employee.getDirectReports().size(); i++) {
        	// Using recursive call to find the number of direct reports
            directReps += getDirectReports(employee.getDirectReports().get(i).getEmployeeId());
        }
        return directReps;
    }

}
