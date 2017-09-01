package my.rpn;

/**
 * This exception is thrown to the user when RPNCalculator fails.
 * 
 * @author NarayanaSwamy
 */
public class RPNException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -138404421593418476L;

	public RPNException(String message) {
		super(message);
	}

	public RPNException(String message, Throwable cause) {
		super(message, cause);
	}

}
