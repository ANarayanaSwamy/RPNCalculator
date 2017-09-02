package my.rpn;

import java.io.Console;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is used to calculate the input based on RPN
 * 
 * @author NarayanaSwamy
 */
public class Calculator implements OperationContext {

	private static final Logger logger = Logger.getLogger("Calculator");
	
	// Create the custom stack to store the state for every operation
	private RPNStack rpnStack = new RPNStack();

	/**
	 * This method will process each of the input line given by the user.
	 * 
	 * @param input
	 *            the input given by the user.
	 * @throws RPNException
	 *             thrown when unable to perform the operation with the given
	 *             set of parameters.
	 */
	public void processInput(String input) throws RPNException {
		String[] tokens = input.split(RPNStack.SPACE);
		for (int i = 0; i < tokens.length; i++) {
			String token = tokens[i];
			Operator operator = Operators.getOperator(token);

			// Current token should be an element (double value)
			// Parse that and add to stack
			if (null == operator) {
				Double realNumber = parseInput(token);
				rpnStack.push(realNumber);
			} else {
				try {
					// Execute the operation and add the result to stack.
					Double result = operator.execute(this);
					rpnStack.push(result);
				}catch(InsufficientParameterException ipe){
					throw new RPNException("Operator " + token + " (position: "
							+ i + "): insufficient parameters");
				} catch (OperationFailedException e) {
					throw new RPNException("Operation "+token+" failed.",e);
				}
			}
		}
	}

	private Double parseInput(String inputToken) throws RPNException {
		try {
			return Double.parseDouble(inputToken);
		} catch (NumberFormatException e) {
			throw new RPNException("Not a valid number/operator ", e);
		}
	}


	// Methods exposed to operations via context
	@Override
	public Double[] getElements(int num) throws InsufficientParameterException {
		return rpnStack.popElements(num);
	}

	@Override
	public void clearParams() {
		rpnStack.clear();
	}

	@Override
	public void undo() throws OperationFailedException {
		rpnStack.restore();
	}

	/**
	 * This method is used to return {@link String} representation of the stack
	 * 
	 * @return {@link String} representation of the stack
	 */
	public String stackAsString() {
		return rpnStack.toString();
	}
	
	
	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		Console console = System.console();
		if (null == console) {
			logger.log(Level.SEVERE, "No Console");
			return;
		}
		String input = console.readLine();
		try {
			while (null != input) {
				// TODO: "exit" condition
				// if("exit".equalsIgnoreCase(input)){
				// break;
				// }

				// Perform operation for the given input
				// and wait for next input
				calculator.processInput(input);
				System.out.println(calculator.stackAsString());
				input = console.readLine();
			}
		} catch (RPNException e) {
			System.out.println(calculator.stackAsString());
			System.err.println(e.getMessage());
		}
	}
}
