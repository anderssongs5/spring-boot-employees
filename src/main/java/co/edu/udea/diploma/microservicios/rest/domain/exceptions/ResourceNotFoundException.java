package co.edu.udea.diploma.microservicios.rest.domain.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3479628269015847486L;

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public ResourceNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ResourceNotFoundException(String arg0) {
		super(arg0);
	}

	public ResourceNotFoundException(Throwable cause) {
		super(cause);
	}
}
