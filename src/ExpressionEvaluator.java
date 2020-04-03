package eg.edu.alexu.csd.datastructure.stack;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * converts infix expressions to postfix expressions and evaluates postfix expressions
 */
public class ExpressionEvaluator implements IExpressionEvaluator {
	
	// auxiliary functions
	
	/**
	 * check if a char is a '+' , '-' , '*' or '/'
	 * 
	 * @param c single character
	 * @return
	 */
	private boolean isOperation(char c) {
		return (c == '+' || c == '-' || c == '*' || c == '/');
	}
	
	/**
	 * checks the validity of the expression 
	 * 
	 * @param expression
	 * @return false if the expression is empty or contains special characters, true otherwise
	 */
	private boolean isValidInfix(String expression) {
		expression = expression.trim();
		if (expression == null || expression.length() == 0 || isOperation(expression.charAt(expression.length() - 1))) {
			return false;
		}
		if(expression.charAt(0) == '(' && expression.charAt(expression.length()-1) == ')') {
			expression = expression.substring(1,expression.length() - 1);
		}
		if(expression.charAt(0) == '*' || expression.charAt(0) == '/' || expression.charAt(0) == '+' ) {
			return false;
		}
		if(expression.length() >= 2 && expression.charAt(0) == '-' && expression.charAt(1) == '-') {
			return false;
		}
		if(!(Pattern.compile("[*+-/]").matcher(expression).find())) {
			try {
				int num = Integer.parseInt(expression);
			}catch(NumberFormatException ex) {
				return false;
			}		
		}
		return !(Pattern.compile("[&^%$#@!?'\\\"~<>=_]").matcher(expression).find());
	}

	/**
	 * prepares the input expression to use it in infixToPostfix
	 * adds a dummy zero if there are negative numbers
	 * @param expression
	 * @return new expression with all negative numbers replaced with subtraction from zero
	 */
	public String prepareExp(String expression) {
		int pointer = 0;
		while (pointer < expression.length()) {
			if (expression.charAt(pointer) == '-') {
				if (pointer == 0 && !isOperation(expression.charAt(pointer + 1))) {
					expression = "(0" + expression;
					int temp = pointer + 1; //to go back to this negative sign again in the next iteration
					pointer += 3;
					if (expression.charAt(pointer) == '(') {
						while (pointer < expression.length() && expression.charAt(pointer) != ')') {
							pointer++;
						}
					} else {
						while (pointer < expression.length() && !isOperation(expression.charAt(pointer))) {
							pointer++;
						}
					}
					expression = expression.substring(0, pointer) + ")" + expression.substring(pointer);
					pointer = temp;
				}else if( (isOperation(expression.charAt(pointer - 1)) || expression.charAt(pointer - 1) == '(' )&& !isOperation(expression.charAt(pointer + 1))) {
					expression = expression.substring(0, pointer) + "(0" + expression.substring(pointer);
					int temp = pointer + 1;
					pointer += 3;
					if (expression.charAt(pointer) == '(') {
						while (pointer < expression.length() && expression.charAt(pointer) != ')') {
							pointer++;
						}
					} else {
						while (pointer < expression.length() && !isOperation(expression.charAt(pointer))) {
							pointer++;
						}
					}
					expression = expression.substring(0, pointer) + ")" + expression.substring(pointer);
					pointer = temp;
				}
				pointer++;

			} else {
				pointer++;
			}
		}
		return expression;
	}

	/**
	 * Takes a symbolic/numeric infix expression as input and converts it to postfix
	 * notation. There is no assumption on spaces between terms or the length of the
	 * term (e.g., two digits symbolic or numeric term)
	 *
	 * @param expression infix expression
	 * @return postfix expression
	 */
	@Override
	public String infixToPostfix(String expression) {
		// check validity
		if (!isValidInfix(expression)) {
			throw new RuntimeException("Invalid infix expression!");
		}
		// remove any white spaces
		expression = expression.replaceAll("\\s", "");
		// handling negative numbers by adding a dummy zero 
		expression = prepareExp(expression);
		String postfixExp = "";
		Stack operations = new Stack();
		int pointer = 0;
		while (pointer < expression.length()) {
			if (pointer != expression.length() - 1 && isOperation(expression.charAt(pointer))
					&& isOperation(expression.charAt(pointer + 1))) {
				throw new RuntimeException("Invalid expression! two consicutive operators.");
			} else if (expression.charAt(pointer) == '+' || expression.charAt(pointer) == '-') {
				if (!operations.isEmpty() && isOperation((char) operations.peek())) {
					postfixExp = postfixExp + operations.pop() + " ";
				} else {
					operations.push(expression.charAt(pointer++));
				}
			} else if (expression.charAt(pointer) == '*' || expression.charAt(pointer) == '/') {
				if (!operations.isEmpty() && ((char) operations.peek() == '*' || (char) operations.peek() == '/')) {
					postfixExp = postfixExp + operations.pop() + " ";
				} else {
					operations.push(expression.charAt(pointer++));
				}
			} else if (expression.charAt(pointer) == '(') {
				operations.push(expression.charAt(pointer++));
			} else if (expression.charAt(pointer) == ')') {
				while (!operations.isEmpty() && (char) operations.peek() != '(') {
					postfixExp = postfixExp + operations.pop() + " ";
				}
				if (!operations.isEmpty() && (char) operations.peek() == '(') {
					operations.pop();
					pointer++;
				} else {
					throw new RuntimeException("Missing left parenthesis!");
				}
			} else {
				int counter = pointer;
				while (pointer < expression.length() && !isOperation(expression.charAt(pointer))
						&& expression.charAt(pointer) != '(' && expression.charAt(pointer) != ')') {
					pointer++;
				}
				postfixExp = postfixExp + expression.substring(counter, pointer) + " ";
			}
		}
		while (!operations.isEmpty()) {
			postfixExp = postfixExp + operations.pop() + " ";
		}
		if (Pattern.compile("[(]").matcher(postfixExp).find()) {
			throw new RuntimeException("Missing right parenthesis!");
		}
		postfixExp = postfixExp.trim();
		return postfixExp;
	}

	/**
	 * Evaluate a postfix numeric expression, with a single space separator
	 *
	 * @param expression postfix expression
	 * @return the expression evaluated value
	 */
	@Override
	public int evaluate(String expression) {
		expression = expression.trim();
		String[] ExpArray = expression.split("[  	]+", 0);
		Stack operations = new Stack();
		for (int i = 0; i < ExpArray.length; i++) {
			try {
				float num = Float.parseFloat(ExpArray[i]);
				operations.push(num);
			} catch (NumberFormatException ex) {
				if (operations.size() < 2) {
					throw new RuntimeException("Invalid postfix expression!");
				}
				float second = (float) operations.pop();
				float first = (float) operations.pop();
				if (ExpArray[i].equals("+")) {
					operations.push(first + second);
				} else if (ExpArray[i].equals("-")) {
					operations.push(first - second);
				} else if (ExpArray[i].equals("*")) {
					operations.push(first * second);
				} else if (ExpArray[i].equals("/")) {
					if (second == 0) {
						throw new ArithmeticException("Invalid! division by zero.");
					}
					operations.push(first / second);
				} else {
					throw new RuntimeException("Invalid postfix expression!");
				}
			}
		}
		if (operations.size() == 1) {
			return (int) (float) operations.pop();
		} else {
			throw new RuntimeException("Invalid postfix expression!");
		}
	}

}
