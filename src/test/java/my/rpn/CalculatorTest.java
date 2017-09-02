package my.rpn;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Calculator
 * 
 * @author NarayanaSwamy
 */
public class CalculatorTest {

	private Calculator calculator;

	@Before
	public void setup() {
		calculator = createCalculator();
	}

	@Test
	public void calculatorShouldAddValuesToStack() throws Exception {
		calculator.processInput("5 2");
		assertStack("stack: 5 2");
	}

	@Test
	public void calculatorCanAddTwoNumbers() throws Exception {
		calculator.processInput("2 3 +");
		assertStack("stack: 5");
	}

	@Test
	public void calculatorCanPerformSumAndProductOperation() throws Exception {
		calculator.processInput("2 3 3 + *");
		assertStack("stack: 12");
	}

	@Test
	public void calculatorCanPerformMultiplicationDivition() throws Exception {
		calculator.processInput("2.5 3 3 * /");
		assertStack("stack: 0.2777777778");
	}

	@Test
	public void calculatorCannotDivideByZero() throws Exception {
		try {
			calculator.processInput("2 0 /");
			Assert.fail("No exception thrown for 0");
		} catch (RPNException e) {
			Assert.assertEquals("Operation / failed.", e.getMessage());
			Assert.assertEquals("Cannot divide by 0", e.getCause().getMessage());
		}
	}

	@Test
	public void calculatorCannotDivideByZeroWhenSomeElementsAreInStack() throws Exception {
		try {
			calculator.processInput("2 5 *");
			assertStack("stack: 10");
			calculator.processInput("0 /");
			Assert.fail("No exception thrown for 0");
		} catch (RPNException e) {
			Assert.assertEquals("Operation / failed.", e.getMessage());
			Assert.assertEquals("Cannot divide by 0", e.getCause().getMessage());
		}
	}
	
	@Test
	public void calculatorCanPerformSquareRootOperation() throws Exception {
		calculator.processInput("2 sqrt");
		assertStack("stack: 1.4142135624");
	}

	@Test
	public void calculatorCanPerfromMultipleOperations() throws Exception {
		calculator.processInput("5 2 -");
		assertStack("stack: 3");
		calculator.processInput("3 -");
		assertStack("stack: 0");
		calculator.processInput("clear");
		assertStack("stack:");
	}

	@Test
	public void calculatorCanPerformOperationWithPreviousStackValues()
			throws Exception {
		calculator.processInput("7 12 2 /");
		assertStack("stack: 7 6");
		calculator.processInput("*");
		assertStack("stack: 42");
		calculator.processInput("4 /");
		assertStack("stack: 10.5");
	}

	@Test
	public void calculatorShouldClearTheStackWhenClearCommandIsGiven()
			throws Exception {
		calculator.processInput("1 2 3 4 5");
		assertStack("stack: 1 2 3 4 5");
		calculator.processInput("*");
		assertStack("stack: 1 2 3 20");
		calculator.processInput("clear 3 4 -");
		assertStack("stack: -1");
	}

	@Test
	public void calculatorCanWorkWhenInputsAreSeparated() throws Exception {
		calculator.processInput("1 2 3 4 5");
		assertStack("stack: 1 2 3 4 5");
		calculator.processInput("* * * *");
		assertStack("stack: 120");
	}

	@Test
	public void calculatorShouldSupportUndoOperation() throws Exception {
		calculator.processInput("5 4 3 2");
		assertStack("stack: 5 4 3 2");
		calculator.processInput("undo undo *");
		assertStack("stack: 20");
		calculator.processInput("5 *");
		assertStack("stack: 100");
		calculator.processInput("undo");
		assertStack("stack: 20 5");
	}

	@Test
	public void calculatorShouldSupportUndoOperationForClear() throws Exception {
		calculator.processInput("5 4 3 2");
		assertStack("stack: 5 4 3 2");
		calculator.processInput("undo undo *");
		assertStack("stack: 20");
		calculator.processInput("clear");
		assertStack("stack:");
		calculator.processInput("undo");
		assertStack("stack: 20");
		
	}

	private void assertStack(String expectedResult) {
		Assert.assertEquals(expectedResult, calculator.stackAsString());		
	}

	@Test
	public void calculatorWillFailWhenInsufficientParametersArePassed() {
		try {
			calculator.processInput("1 2 3 * 5 + * * 6 5");
			Assert.fail("No exception is thrown when parameters are not sufficient");
		} catch (RPNException expected) {
			Assert.assertEquals(
					"Operator * (position: 7): insufficient parameters",
					expected.getMessage());
		}
	}

	
	private Calculator createCalculator() {
		Calculator calculator = new Calculator();
		return calculator;
	}
}
