/**
 * 
 */
package com.unit.testing.junit.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Rambhajan Sonti
 * 13-Feb-2022
 * 5:26:45 pm
 */
class TestClassTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() { 
//		fail("Test cases ran");
		TestClass obj=new TestClass();
		int res=obj.add(1, 2);
		int expected=2;
		assertEquals(expected, res);
		
	}

}
