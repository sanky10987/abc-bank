package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TransactionTest {
	@Test
	public void transaction() {
		Transaction t = new Transaction(5);
		assertEquals(5, t.amount, 1);
	}
}
