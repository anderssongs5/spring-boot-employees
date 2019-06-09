package co.edu.udea.diploma.microservicios.rest.domain.exceptions;

public class ExistingResourceException extends RuntimeException {

	private static final long serialVersionUID = -3479628269015847486L;

	public ExistingResourceException() {
		super();
	}

	public ExistingResourceException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public ExistingResourceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ExistingResourceException(String arg0) {
		super(arg0);
	}

	public ExistingResourceException(Throwable cause) {
		super(cause);
	}
}
