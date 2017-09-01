package my.rpn;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Stack;

/**
 * This class is used to store the stack of elements This also stores the state
 * of the stack using Momentos when any state change happens
 * 
 * @author NarayanaSwamy
 */
public class RPNStack {

	// Stores the elements
	private Stack<Double> stack = new Stack<Double>();

	// This will hold all the previous state, as undo can be given at any time.
	private Stack<RPNState> undoStates = new Stack<RPNState>();

	// This value is used to control the maximum number of undo entries that can be 
	// used. -1 represents unlimited
	// TODO : Read this from configuration
	private int maxUndoSize = -1;

	public static final String SPACE = " ";

	// This class is used to store the previous state
	class RPNState {
		private Stack<Double> state;

		public RPNState(Stack<Double> state) {
			this.state = copyStack(state);
		}

		private Stack<Double> copyStack(Stack<Double> state) {
			Stack<Double> tempStack = new Stack<Double>();
			tempStack.addAll(state);
			return tempStack;
		}

		public Stack<Double> getState() {
			return state;
		}
	}

	/**
	 * This method will push the given element to the stack, this will also
	 * store the previous state.
	 * 
	 * @param value
	 *            Value that has to be pushed to the stack.
	 */
	public void push(Double value) {
		// No need to add the null return values
		if (null == value) {
			return;
		}

		// Store the state only when required
		if (false == stack.isEmpty()) {
			saveState();
		}
		stack.push(value);
	}

	private void saveState() {
		// If max cache is reached, then remove the oldest one
		if(maxUndoSize > 0  && undoStates.size() >= maxUndoSize){
			undoStates.remove(0);
		}
		undoStates.push(new RPNState(stack));
	}

	/**
	 * This method is used to pop the required number of elements from the
	 * stack. This method will store the stack state before popping the
	 * elements.
	 * 
	 * @param num
	 *            Number of elements to be popped from Stack
	 * @return Popped elements
	 * @throws OperationFailedException
	 *             When elements is not enough to be popped.
	 */
	public Double[] popElements(int num) throws OperationFailedException {
		if (stack.size() < num) {
			throw new OperationFailedException("Insufficient parameters");
		}
		saveState();
		Double[] res = new Double[num];
		for (int i = 0; i < num; i++) {
			res[i] = stack.pop();
		}
		return res;
	}

	/**
	 * This method clears the stack.
	 */
	public void clear() {
		saveState();
		stack.clear();
	}

	/**
	 * This method will restore the stack data 
	 */
	public void restore() {
		RPNState momento = undoStates.pop();
		stack = momento.getState();
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("stack: ");
		Iterator<Double> iterator = stack.iterator();
		while (iterator.hasNext()) {
			res.append(format(iterator.next()));
			res.append(SPACE);
		}
		return res.toString().trim();
	}

	// Format the Double to 10 decimal place for display
	private String format(Double value) {
		DecimalFormat fmt = new DecimalFormat("0.##########");
		return fmt.format(value);
	}
}
