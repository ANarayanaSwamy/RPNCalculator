package my.rpn;

import java.util.HashMap;
import java.util.Map;

/**
 * This repository class holds all the available {@link Operator} in the system.
 * 
 * @author NarayanaSwamy
 */
public class Operators {

	private static final Map<String, Operator> operators = new HashMap<String, Operator>();

	private static final Operator PLUS_OPERATOR = new Operator() {
		@Override
		public Double execute(OperationContext context)
				throws OperationFailedException {
			Double[] elements = context.getElements(2);
			Double topElement = elements[0];
			Double nextElement = elements[1];
			return topElement + nextElement;
		}
	};
	private static final Operator MINUS_OPERATOR = new Operator() {

		@Override
		public Double execute(OperationContext context)
				throws OperationFailedException {
			Double[] elements = context.getElements(2);
			Double topElement = elements[0];
			Double nextElement = elements[1];
			return nextElement - topElement;
		}

	};
	private static final Operator MULTIPLY_OPERATOR = new Operator() {

		@Override
		public Double execute(OperationContext context)
				throws OperationFailedException {
			Double[] elements = context.getElements(2);
			Double topElement = elements[0];
			Double nextElement = elements[1];
			return topElement * nextElement;
		}

	};
	private static final Operator DIVIDE_OPERATOR = new Operator() {

		@Override
		public Double execute(OperationContext context)
				throws OperationFailedException {
			Double[] elements = context.getElements(2);
			Double topElement = elements[0];
			Double nextElement = elements[1];
			if(topElement == 0){
				throw new OperationFailedException("Cannot divide by 0");
			}
			return nextElement / topElement;
		}

	};
	private static final Operator SQRT_OPERATOR = new Operator() {

		@Override
		public Double execute(OperationContext context)
				throws OperationFailedException {
			Double firstParam = context.getElements(1)[0];
			return Math.sqrt(firstParam);
		}

	};

	private static final Operator CLEAR_OPERATOR = new Operator() {

		@Override
		public Double execute(OperationContext context) {
			context.clearParams();
			return null;
		}

	};

	private static final Operator UNDO_OPERATOR = new Operator() {

		@Override
		public Double execute(OperationContext context)
				throws OperationFailedException {
			context.undo();
			return null;
		}

	};
	
	// Populate the available operators
	static {
		operators.put("+", PLUS_OPERATOR);
		operators.put("-", MINUS_OPERATOR);
		operators.put("*", MULTIPLY_OPERATOR);
		operators.put("/", DIVIDE_OPERATOR);
		operators.put("sqrt", SQRT_OPERATOR);
		operators.put("clear", CLEAR_OPERATOR);
		operators.put("undo", UNDO_OPERATOR);
	}

	/**
	 * This lookup method is used to find the operator reference based on the
	 * given input
	 * 
	 * @param operation
	 *            represents the operation specified by the user
	 * @return {@link Operator} that matches to the user given input.
	 */
	public static Operator getOperator(String operation) {
		return operators.get(operation);
	}

}
