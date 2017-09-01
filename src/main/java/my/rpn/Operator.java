package my.rpn;

/**
 * This interface is used to generalize all the operations. All the operator
 * will be implementing this interface which will be pooled by the Operators.
 * 
 * @author NarayanaSwamy
 */
public interface Operator {
	/**
	 * This callback method will be invoked from {@link Calculator} for the
	 * corresponding operation
	 * 
	 * @param context
	 *            Calculator context that will be given for the operation to
	 *            read the stack data
	 * @return the operation result
	 * @throws OperationFailedException
	 *             When any failure during the operation.
	 * 
	 */
	public Double execute(OperationContext context)
			throws OperationFailedException;
}
