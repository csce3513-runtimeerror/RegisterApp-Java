package main.java.edu.uark.registerapp.commands.employees;

import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.uark.registerapp.commands.ResultCommandInterface;
import edu.uark.registerapp.commands.exceptions.NotFoundException;
import edu.uark.registerapp.commands.exceptions.UnprocessableEntityException;
import edu.uark.registerapp.models.api.Employee;
import edu.uark.registerapp.models.entities.EmployeeEntity;
import edu.uark.registerapp.models.repositories.EmployeeRepository;

@Service
public class EmployeeUpdateCommand implements ResultCommandInterface<Employee> {
	@Transactional
	@Override
	public Employee execute() {
		this.validateProperties();

		final Optional<EmployeeEntity> employeeEntity =
			this.employeeRepository.findById(this.employeeId);
		if (!employeeEntity.isPresent()) { // No record with the associated record ID exists in the database.
			throw new NotFoundException("Employee");
		}

		// Synchronize any incoming changes for UPDATE to the database.
		this.apiEmployee = employeeEntity.get().synchronize(this.apiEmployee);

		// Write, via an UPDATE, any changes to the database.
		this.employeeRepository.save(employeeEntity.get());

		return this.apiEmployee;
	}

	// Helper methods
	private void validateProperties() {
		if (StringUtils.isBlank(this.apiEmployee.getFirstName())) {
			throw new UnprocessableEntityException("firstname");
        }
        if (StringUtils.isBlank(this.apiEmployee.getLastName())) {
			throw new UnprocessableEntityException("lastname");
		}
	}

	// Properties
	private UUID employeeId;
	public UUID getEmployeeId() {
		return this.employeeId;
	}
	public EmployeeUpdateCommand setEmployeeId(final UUID employeeId) {
		this.employeeId = employeeId;
		return this;
	}

	private Employee apiEmployee;
	public Employee getApiEmployee() {
		return this.apiEmployee;
	}
	public EmployeeUpdateCommand setApiEmployee(final Employee apiEmployee) {
		this.apiEmployee = apiEmployee;
		return this;
	}
	
	@Autowired
	private EmployeeRepository employeeRepository;
}