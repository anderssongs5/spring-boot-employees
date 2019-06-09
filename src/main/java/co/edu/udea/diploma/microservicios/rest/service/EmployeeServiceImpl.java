package co.edu.udea.diploma.microservicios.rest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import co.edu.udea.diploma.microservicios.rest.domain.Employee;
import co.edu.udea.diploma.microservicios.rest.domain.exceptions.ExistingResourceException;
import co.edu.udea.diploma.microservicios.rest.domain.exceptions.ResourceNotFoundException;
import co.edu.udea.diploma.microservicios.rest.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeServiceImpl(final EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Iterable<Employee> getAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee save(Employee employee) {
		Assert.isTrue(employee != null, "You can't send an empty employee");
		Assert.isTrue(employee.getId() != null && !employee.getId().trim().isEmpty(), "You can't send an empty id");
		Assert.isTrue(employee.getName() != null && !employee.getName().trim().isEmpty(),
				"You can't send an empty name");
		Assert.isTrue(employee.getLastName() != null && !employee.getLastName().trim().isEmpty(),
				"You can't send an empty last name");

		if (employeeRepository.existsById(employee.getId())) {
			throw new ExistingResourceException("Employee already exists - id " + employee.getId());
		}

		return employeeRepository.save(employee);
	}

	@Override
	public Optional<Employee> getById(String id) {
		Assert.isTrue(id != null && !id.trim().isEmpty(), "You can't send an empty id");

		return employeeRepository.findById(id);
	}

	@Override
	public Employee update(String id, Employee employee) {
		Assert.isTrue(id != null && !id.trim().isEmpty(), "You can't send an empty id");
		Assert.isTrue(employee != null, "You can't send an empty employee");
		Assert.isTrue(employee.getName() != null && !employee.getName().trim().isEmpty(),
				"You can't send an empty name");
		Assert.isTrue(employee.getLastName() != null && !employee.getLastName().trim().isEmpty(),
				"You can't send an empty last name");
		Employee e = new Employee(id, employee.getName(), employee.getLastName());

		if (employeeRepository.findById(id) == null) {
			throw new ResourceNotFoundException("Employee not found - id " + id);
		}

		return employeeRepository.save(e);
	}

	@Override
	public void delete(String id) {
		Assert.isTrue(id != null && !id.trim().isEmpty(), "You can't send an empty id");

		employeeRepository.deleteById(id);
	}

}
