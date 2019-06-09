package co.edu.udea.diploma.microservicios.rest.service;

import java.util.Optional;

import co.edu.udea.diploma.microservicios.rest.domain.Employee;

public interface EmployeeService {

	Iterable<Employee> getAll();

	Employee save(Employee employee);

	Optional<Employee> getById(String id);

	Employee update(String id, Employee employee);

	void delete(String id);
}
