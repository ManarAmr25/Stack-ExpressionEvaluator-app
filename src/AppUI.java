package eg.edu.alexu.csd.datastructure.stack;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Expression evaluator application
 */
public class AppUI {

	static private ExpressionEvaluator e = new ExpressionEvaluator();

	public static void main(String[] args) {
		// Setting menu
		System.out.print("\nStart test\n" + "Choose  an operation :\n"
				+ "\t1: convert an infix expression (symbolic or numeric) to postfix expression.\n"
				+ "\t2: evaluate numeric a postfix expression.\n" + "Enter -1 to Exit , 0 to go back to menu\n->>");

		// Taking user's choice
		Scanner input = new Scanner(System.in);
		int choice = 0;

		// Testing input validity
		try {
			choice = input.nextInt();
		} catch (InputMismatchException ex) {
			System.out.println("Invalid! Choose an integer from 1 to 2.");
			main(null);
			input.close();
			return;
		}

		switch (choice) {
		case -1: // Exit
			System.out.println("Program terminated.");
			break;
		case 0: // back to menu
			main(null);
			break;
		case 1: //infix to postfix
			System.out.print("Enter an expression : ");
			input.nextLine();
			String infixExp = input.nextLine();
			try {
				String output = e.infixToPostfix(infixExp);
				System.out.printf("Output : %s\n" , output);
				int choice1 = 1;
				while (output.matches(".*[a-zA-Z].*")) { 
				    System.out.println("Do you want to enter values for symbols?\n\t1:yes\n\t2:No");
				    choice1 = input.nextInt();
				    if(choice1 == 1) {
				    	System.out.print("Enter symbol to replace : ");
						String symbol = input.next();
						System.out.print("Enter value : ");
						String value = input.next();
						output = output.replaceAll(symbol, value);
						System.out.printf("Output : %s\n" , output);
				    }else {
				    	break;
				    }
				}
				if(!output.matches(".*[a-zA-Z].*")) {
					System.out.println("Do you want to evaluate this expression ?\n\t1:yes\n\t2:No");
				    choice1 = input.nextInt();
				    if(choice1 == 1) {
				    	try {
							System.out.printf("Output : %d\n" , e.evaluate(output));
						} catch (RuntimeException ex) {
							System.out.println("Invalid!");
						}
				    }
				}
			}catch(RuntimeException ex) {
				System.out.println("Invalid!");
			}	
			main(null);
			break;
		case 2: // evaluate postfix
			System.out.print("Enter an expression : ");
			input.nextLine();
			String postfixExp = input.nextLine();
			try {
				int output = e.evaluate(postfixExp);
				System.out.printf("Output : %d\n" , output);
			} catch (RuntimeException ex) {
				System.out.println("Invalid!");
			}
			main(null);
			break;
		default: // wrong choice
			System.out.print("Invalid choice!");
			main(null);
		}
		input.close();

	}

}
