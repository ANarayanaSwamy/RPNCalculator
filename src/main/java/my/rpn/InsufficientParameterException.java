package my.rpn;

/**
 * This exception is thrown when parameters are not sufficient to perform the operation.
 * 
 * @author NarayanaSwamy
 */
public class InsufficientParameterException extends OperationFailedException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9142004441097964893L;

	public InsufficientParameterException(String message){
		super(message);
	}
}
