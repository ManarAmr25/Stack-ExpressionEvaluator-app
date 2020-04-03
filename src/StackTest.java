package eg.edu.alexu.csd.datastructure.stack;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

import org.junit.jupiter.api.Test;

class StackTest {
	
	
	/**
	 * testing size and isEmpty methods
	 */
	@Test
	void test0() {
		
		Stack s = new Stack();
		assertEquals(s.size(),0);
		assertTrue(s.isEmpty());
		
		s.push(10);
		assertEquals(s.size(),1);
		assertFalse(s.isEmpty());
		
		s.push("Hello world!");
		assertEquals(s.size(),2);
		assertFalse(s.isEmpty());
		
		s.push(95.3);
		assertEquals(s.size(),3);
		assertFalse(s.isEmpty());
		
		assertThrows(RuntimeException.class, () -> {s.push(null);});
		assertEquals(s.size(),3);
		assertFalse(s.isEmpty());
		
		s.pop();
		assertEquals(s.size(),2);
		assertFalse(s.isEmpty());
		
		s.pop();
		assertEquals(s.size(),1);
		assertFalse(s.isEmpty());
		
		s.pop();
		assertEquals(s.size(),0);
		assertTrue(s.isEmpty());
		
		assertThrows(RuntimeException.class, () -> {s.pop();});
		assertEquals(s.size(),0);
		assertTrue(s.isEmpty());
		 
	}
	
	/**
	 * testing pop , push and peek methods
	 */
	@Test
	void test1() {
		
		Stack s = new Stack();
		int[] arr = {0,1,2,3,4};
		Point p = new Point();
		
		assertThrows(RuntimeException.class, () -> {s.peek();});
		assertThrows(RuntimeException.class, () -> {s.pop();});
		
		assertThrows(RuntimeException.class, () -> {s.push(null);});
		
		assertThrows(RuntimeException.class, () -> {s.peek();});
		assertThrows(RuntimeException.class, () -> {s.pop();});
		
		s.push(500);
		s.push(30.51);
		s.push("Hello world!");
		s.push(p);
		s.push(arr);
		assertThrows(RuntimeException.class, () -> {s.push(null);});
		
		assertArrayEquals(arr,(int[])s.peek());
		assertArrayEquals(arr,(int[])s.pop());
		
		assertEquals(p, (Point)s.peek());
		assertEquals(p, (Point)s.pop());
		
		assertEquals("Hello world!", (String)s.peek());
		assertEquals("Hello world!", (String)s.pop());
		
		assertEquals(30.51, (Double)s.peek());
		assertEquals(30.51, (Double)s.pop());
		
		assertEquals(500, (int)s.peek());
		assertEquals(500, (int)s.pop());
		
		assertThrows(RuntimeException.class, () -> {s.peek();});
		assertThrows(RuntimeException.class, () -> {s.pop();});
	}
	

}
