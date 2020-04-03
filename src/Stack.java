package eg.edu.alexu.csd.datastructure.stack;

/**
 * Linked List implementation for stack ADT.
 */
public class Stack implements IStack {

	/**
	 * represents a singly linked list node. Can carry any type of objects.
	 */
	private class Node {
		Object data;
		Node next;

		public Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	private Node bottom = null;
	private Node top = null;
	private int size = 0;

	
	/**
	 * Removes the element at the top of stack and returns that element.
	 *
	 * @return top of stack element, or through exception if empty
	 */
	@Override
	public Object pop() {
		if (isEmpty()) {
			throw new RuntimeException("Stack is empty!");
		} else {
			Object element = top.data;
			if (top.equals(bottom)) {
				bottom = null;
			}
			top = top.next;
			size--;
			return element;
		}
	}


	/**
	 * Get the element at the top of stack without removing it from stack.
	 *
	 * @return top of stack element, or through exception if empty
	 */
	@Override
	public Object peek() {
		if (isEmpty()) {
			throw new RuntimeException("Stack is empty!");
		} else {
			return top.data;
		}
	}

	
	/**
	 * Pushes an item onto the top of this stack.
	 *
	 * @param object to insert
	 */
	@Override
	public void push(Object element) {
		if(element == null) {
			throw new RuntimeException("Can not push a null element!");
		}
		Node newNode = new Node(element, top);
		top = newNode;
		if (isEmpty()) {
			bottom = newNode;
		}
		size++;
	}

	
	/**
	 * Tests if this stack is empty
	 *
	 * @return true if stack empty
	 */
	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	
	/**
	 * Returns the number of elements in the stack.
	 *
	 * @return number of elements in the stack
	 */
	@Override
	public int size() {
		return size;
	}
	
}
