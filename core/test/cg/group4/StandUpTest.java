package cg.group4;

import static org.junit.Assert.*;

import org.junit.Test;

public class StandUpTest {

	@Test
	public void test() {
		int x = 5;
		assertTrue(x == 5);
	}
	
	@Test
	public void test1() {
		assertTrue(StandUp.testerooni() == 2);
	}
}