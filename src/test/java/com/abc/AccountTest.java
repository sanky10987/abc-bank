package com.abc;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AccountTest {

	private Account account;

	@Before
	public void setUp() throws Exception {
		account = new Account(Account.MAXI_SAVINGS);
	}

	@Test
	public void testDeposit() {
		account.deposit(10);
		assertEquals(10.0, account.getTransactions().get(0).amount, 1);
	}

	@Test
	public void testWithdrawal() {
		account.deposit(10);
		account.withdraw(10);
		assertEquals(-10.0, account.getTransactions().get(1).amount, 1);
	}

	@Test
	public void testTransactions() {
		account.deposit(10);
		account.withdraw(10);
		assertEquals(2, account.getTransactions().size());
	}

	@Test
	public void testCheckIfWithdrawalsExistBeforeNDays() {
		account.deposit(10);
		account.withdraw(10);
		account.deposit(10);
		account.deposit(10);
		assertEquals(0, account.interestEarned(), 1);
	}

	@After
	public void tearDown() throws Exception {
		account = null;
	}

}
