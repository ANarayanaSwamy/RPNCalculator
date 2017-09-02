package my.rpn;

/**
 * This is used by the Operations to access the stack
 * 
 * @author NarayanaSwamy
 */
public interface OperationContext {

	/**
	 * This method is used to get the required number of elements from the stack
	 * for performing the operation.
	 * 
	 * @param num
	 *            Number of elements to be popped from Stack
	 * @return Values from stack
	 * @throws InsufficientParameterException
	 *             When the elements are not sufficient as per the request.
	 */
	public Double[] getElements(int num) throws InsufficientParameterException;

	/**
	 * This method clears the stack.
	 */
	public void clearParams();

	/**
	 * This method will restore the stack data to previous state.
	 */
	public void undo() throws OperationFailedException;

}
