package co.edu.udea.diploma.microservicios.rest.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.udea.diploma.microservicios.rest.domain.Employee;
import co.edu.udea.diploma.microservicios.rest.domain.exceptions.ExistingResourceException;
import co.edu.udea.diploma.microservicios.rest.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/v1")
@Api(value="Employee Management System")
public final class EmployeeController {

	private EmployeeService employeeService;
	private static final String UNEXPECTED_ERROR = "An unexpected error has occurred";

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@ApiOperation(value = "View a list of available employees", response = Iterable.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully retrieved list"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping(value = "/employees")
	public ResponseEntity<?> getAll() {
		try {
			Iterable<Employee> employees = this.employeeService.getAll();
			if (employees != null && employees.iterator().hasNext()) {
				return ResponseEntity.ok(employees);
			}

			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().body(UNEXPECTED_ERROR);
		}
	}

	@ApiOperation(value = "Get an employee by Id")
	@GetMapping(value = "/employees/{id}")
	public ResponseEntity<?> getById(@ApiParam(value = "Employee id from which employee object will retrieve", required = true)
									@PathVariable("id") String id) {
		try {
			Optional<Employee> e = this.employeeService.getById(id);
			if (e.isPresent()) {
				return ResponseEntity.ok(e.get());
			}

			return ResponseEntity.notFound().build();
		} catch (IllegalArgumentException e) {
			throw e;
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().body(UNEXPECTED_ERROR);
		}
	}

	@ApiOperation(value = "Add an employee")
	@PostMapping(value = "/employees")
	public ResponseEntity<?> createEmployee(@ApiParam(value = "Employee object store in database table", required = true) @Valid
					@RequestBody Employee employee) {
		try {
			return ResponseEntity.ok(this.employeeService.save(employee));
		} catch (ExistingResourceException e) {
			throw e;
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().body(UNEXPECTED_ERROR);
		}
	}

	@ApiOperation(value = "Update an employee")
	@PutMapping(value = "/employees/{id}")
	public ResponseEntity<?> updateEmployee(@ApiParam(value = "Employee Id to update employee object", required = true) 
						@PathVariable("id") String id,
						@ApiParam(value = "Update employee object", required = true) @Valid
						@RequestBody Employee employee) {
		try {
			return ResponseEntity.ok(this.employeeService.update(id, employee));
		} catch (ExistingResourceException e) {
			throw e;
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().body(UNEXPECTED_ERROR);
		}
	}

	@ApiOperation(value = "Delete an employee")
	@DeleteMapping(value = "/employees/{id}")
	public ResponseEntity<?> deleteEmployee(@ApiParam(value = "Employee Id from which employee object will delete from database table", required = true)
					@PathVariable("id") String id) {
		try {
			this.employeeService.delete(id);
			return ResponseEntity.ok().build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().body(UNEXPECTED_ERROR);
		}
	}
}
