package com.abc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest {
	private static double DOUBLE_DELTA = 1e-15;
	private Bank bank;

	@Before
	public void setUp() {
		bank = new Bank();
	}

	@Test
	public void customerSummary() {
		Customer john = new Customer("John");
		john.openAccount(new Account(Account.CHECKING));
		bank.addCustomer(john);

		assertEquals("Customer Summary\n - John (1 account)",
				bank.customerSummary());
	}

	@Test
	public void checkingAccount() {
		Account checkingAccount = new Account(Account.CHECKING);
		Customer bill = new Customer("Bill");
		bill.openAccount(checkingAccount);
		bank.addCustomer(bill);
		checkingAccount.deposit(100.0);
		assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
	}

	@Test
	public void savings_account() {
		Account savingsAccount = new Account(Account.SAVINGS);
		Customer bill = new Customer("Bill");
		bill.openAccount(savingsAccount);
		bank.addCustomer(bill);
		savingsAccount.deposit(1500.0);
		assertEquals(1.0027397260273974, bank.totalInterestPaid(), DOUBLE_DELTA);
	}

	@Test
	public void maxi_savings_account() {
		Account maxiSavingsAccount = new Account(Account.MAXI_SAVINGS);
		Customer bill = new Customer("Bill");
		bill.openAccount(maxiSavingsAccount);
		bank.addCustomer(bill);
		maxiSavingsAccount.deposit(3000.0);
		assertEquals(0.410958904109589, bank.totalInterestPaid(), DOUBLE_DELTA);
	}

	@Test
	public void test_get_first_customer() {
		bank.addCustomer(new Customer("Bill"));
		assertEquals(bank.getFirstCustomer().getName(), "Bill");
	}

	@After
	public void tearDown() {
		bank = null;
	}

}
