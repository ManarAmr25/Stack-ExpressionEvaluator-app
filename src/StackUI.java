package eg.edu.alexu.csd.datastructure.stack;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Tests stack methods, A stack of Strings is used for test.
 * @param args
 */
public class StackUI {
	
	//Creating an Instance of a stack object 
	static Stack stack = new Stack();
	
	public static void main(String[] args) {
		//Setting menu
		System.out.print("\nStart test\n"
				+ "Enter operation :\n"
				+ "\t1: Push\n"
				+ "\t2: Pop\n"
				+ "\t3: Peek\n"
				+ "\t4: Get size\n"
				+ "\t5: Check if empty\n"
				+ "Enter -1 to Exit , 0 to go back to menu\n->>");

		// Taking user's choice
		Scanner input = new Scanner(System.in);
		int choice = 0;

		// Testing input validity
		try {
			choice = input.nextInt();
		} catch (InputMismatchException ex) {
			System.out.println("Invalid! Choose an integer from 1 to 5.");
			main(null);
			return;
		}

		// Testing operations
		switch (choice) {
		case -1: // Exit
			System.out.println("Program terminated.");
			break;
		case 0: // back to menu
			main(null);
			break;
		case 1: // push
			System.out.print("Enter a string: ");
			input.nextLine();
			String element = input.nextLine();
			stack.push(element);
			main(null);
			break;
		case 2: // pop
			try {
				System.out.printf("Popped element: %s\n", stack.pop());
			} catch (RuntimeException ex) {
				System.out.println("Invalid! Stack is empty.");
			}
			main(null);
			break;
		case 3: // peek
			try {
				System.out.printf("Top element: %s\n", stack.peek());
			} catch (RuntimeException ex) {
				System.out.println("Invalid! Stack is empty.");
			}
			main(null);
			break;
		case 4: // size
			System.out.printf("Size: %s\n", stack.size());
			main(null);
			break;
		case 5: // isEmpty
			if (stack.isEmpty()) {
				System.out.println("Empty.");
			} else {
				System.out.println("Not empty.");
			}
			main(null);
			break;
		default: // wrong choice
			System.out.print("Invalid choice!");
			main(null);
		}

	}

}
