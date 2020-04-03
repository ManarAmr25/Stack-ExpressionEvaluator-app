package eg.edu.alexu.csd.datastructure.stack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
/**
 * Test for ExpressionEvaluator class.
 */
class AppTest {
	
	/**
	 * testing infixToPostfix method
	 */
	@Test
	void test0() {
		ExpressionEvaluator e = new ExpressionEvaluator();
		
		assertEquals("2 3 4 * +", e.infixToPostfix("2 + 3 * 4"));
		assertEquals("a b * 5 +", e.infixToPostfix("a * b + 5"));
		assertEquals("1 2 + 7 *", e.infixToPostfix("(1 + 2) * 7"));
		assertEquals("a b * c /", e.infixToPostfix("a * b / c"));
		assertEquals("a b c - d + / e a - * c *", e.infixToPostfix("(a / (b - c + d)) * (e - a) * c"));
		assertEquals("a b / c - d e * + a c * -", e.infixToPostfix("a / b - c + d * e - a * c"));
		assertEquals("a b c + * d *", e.infixToPostfix("a * (b + c) * d"));
		assertEquals("100", e.infixToPostfix("100"));
		assertEquals("0 100 -", e.infixToPostfix("-100"));
		assertEquals("0 10 - 0 10 - * 0 2 - 3 / +", e.infixToPostfix(" -10 * -10 +-2 /3 "));
		assertEquals("0 20 -", e.infixToPostfix("-(20)"));
		assertEquals("0 20 -", e.infixToPostfix("(-20)"));
		assertEquals("5 0 10 2 + - *", e.infixToPostfix("5*-(10+2)"));
		assertEquals("0 0 8 - -", e.infixToPostfix("-(-8)"));//*************
		assertEquals("5 0 8 - -", e.infixToPostfix("5-(-8)"));
		assertEquals("51", e.infixToPostfix("(51)"));
		assertEquals("3a 10b 0c * +", e.infixToPostfix("3a + 10b * 0c"));
		assertEquals("0 pi - 5 +", e.infixToPostfix("-pi + 5 "));
		
		assertThrows(RuntimeException.class, () -> {e.infixToPostfix("	  	");});
		assertThrows(RuntimeException.class, () -> {e.infixToPostfix("");});
		assertThrows(RuntimeException.class, () -> {e.infixToPostfix("*");});
		assertThrows(RuntimeException.class, () -> {e.infixToPostfix("10 20 30");});
		assertThrows(RuntimeException.class, () -> {e.infixToPostfix("5 * (10 + 15 ");});
		assertThrows(RuntimeException.class, () -> {e.infixToPostfix("5 * 10 + 15)");});
		assertThrows(RuntimeException.class, () -> {e.infixToPostfix("*7");});
		assertThrows(RuntimeException.class, () -> {e.infixToPostfix("7*");});
		assertThrows(RuntimeException.class, () -> {e.infixToPostfix("---20");});
		assertThrows(RuntimeException.class, () -> {e.infixToPostfix("20 11 * 2 +");});
	}
	
	
	/**
	 * testing evaluate method
	 */
	@Test
	void test1() {
		ExpressionEvaluator e = new ExpressionEvaluator();
		
		assertEquals(14 , e.evaluate("2 3 4 * +"));
		assertEquals(21 , e.evaluate("1 2 + 7 *"));
		assertEquals(8 , e.evaluate("6 2 / 3 - 4 2 * +"));
		assertEquals(101 , e.evaluate("101"));
		assertEquals(-25 , e.evaluate("-25"));
		assertEquals(111 , e.evaluate("-10 -3 *  81 +"));
		
		assertThrows(RuntimeException.class, () -> {e.evaluate("	  	");}); 
		assertThrows(RuntimeException.class, () -> {e.evaluate("");});
		assertThrows(RuntimeException.class, () -> {e.evaluate("*");});
		assertThrows(RuntimeException.class, () -> {e.evaluate("10 20 30");});
		assertThrows(RuntimeException.class, () -> {e.evaluate("5 *");});
		assertThrows(RuntimeException.class, () -> {e.evaluate("( 5 10 + )");});
		assertThrows(RuntimeException.class, () -> {e.evaluate("* 7");});
		assertThrows(RuntimeException.class, () -> {e.evaluate("7 5 3 *");});
		assertThrows(RuntimeException.class, () -> {e.evaluate("20 7+ 2- 10*");});
		assertThrows(RuntimeException.class, () -> {e.evaluate("a @ + 10 -");});
		assertThrows(RuntimeException.class, () -> {e.evaluate("--20 10 *");});
		assertThrows(ArithmeticException.class, () -> {e.evaluate("20 5 + 0 /");}); 
 
	}

}
