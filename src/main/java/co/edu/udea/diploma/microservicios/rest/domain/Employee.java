package co.edu.udea.diploma.microservicios.rest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
// @Table(name = "EMPLOYEES")
@RequiredArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@ApiModel(description = "All details about the Employee. ")
public final class Employee {

	@Id
	@Column(name = "ID")
	@ApiModelProperty(notes = "Employee's ID like CC, TI, PA, etc.")
	private final String id;

	// @Column(name = "NAME", nullable = false)
	@ApiModelProperty(notes = "The employee first name")
	private final String name;

	// @Column(name = "LASTNAME", nullable = false)
	@ApiModelProperty(notes = "The employee last name")
	private final String lastName;

	// Empty constructor
	Employee() {
		this(null, null, null);
	}
}
