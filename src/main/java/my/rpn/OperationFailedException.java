package my.rpn;

/**
 * This exception is thrown when any operation is failed to execute.
 * 
 * @author NarayanaSwamy
 */
public class OperationFailedException extends Exception {

	private static final long serialVersionUID = 2432447248827048565L;

	public OperationFailedException(String message) {
		super(message);
	}
}
