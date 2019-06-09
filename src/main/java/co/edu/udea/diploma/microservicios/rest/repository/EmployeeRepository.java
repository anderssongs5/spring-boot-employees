package co.edu.udea.diploma.microservicios.rest.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.udea.diploma.microservicios.rest.domain.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, String> {

}
