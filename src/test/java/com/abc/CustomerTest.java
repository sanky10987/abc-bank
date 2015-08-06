package com.abc;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

	private Customer oscar;

	@Before
	public void setUp() {
		oscar = new Customer("Oscar");
	}

	@Test
	// Test customer statement generation
	public void testApp() {

		Account checkingAccount = new Account(Account.CHECKING);
		Account savingsAccount = new Account(Account.SAVINGS);
		oscar.openAccount(checkingAccount);
		oscar.openAccount(savingsAccount);

		checkingAccount.deposit(100.0);
		savingsAccount.deposit(4000.0);
		savingsAccount.withdraw(200.0);

		assertEquals("Statement for Oscar\n" + "\n" + "Checking Account\n"
				+ "  deposit $100.00\n" + "Total $100.00\n" + "\n"
				+ "Savings Account\n" + "  deposit $4,000.00\n"
				+ "  withdrawal $200.00\n" + "Total $3,800.00\n" + "\n"
				+ "Total In All Accounts $3,900.00", oscar.getStatement());
	}

	@Test
	public void testOneAccount() {
		oscar.openAccount(new Account(Account.SAVINGS));
		assertEquals(1, oscar.getNumberOfAccounts());
	}

	@Test
	public void testTwoAccount() {
		oscar.openAccount(new Account(Account.SAVINGS));
		oscar.openAccount(new Account(Account.CHECKING));
		assertEquals(2, oscar.getNumberOfAccounts());
	}

	@Ignore
	public void testThreeAcounts() {
		oscar.openAccount(new Account(Account.SAVINGS));
		oscar.openAccount(new Account(Account.CHECKING));
		assertEquals(3, oscar.getNumberOfAccounts());
	}

	@Test
	public void testTransferBetweenCheckingAndSavingsAccounts() {
		Account checkingAccount = new Account(Account.CHECKING);
		Account savingsAccount = new Account(Account.SAVINGS);
		oscar.openAccount(checkingAccount);
		oscar.openAccount(savingsAccount);

		savingsAccount.deposit(4000.0);
		oscar.transferBetweenAccounts(savingsAccount, checkingAccount, 500);
		
		assertEquals(3500.0, savingsAccount.sumTransactions(), 2);
		
		assertEquals(500.0, checkingAccount.sumTransactions(), 2);
	}
	
	@Test
	public void testTransferBetweenSavingsAndMaxiAccounts() {
		Account savingsAccount = new Account(Account.SAVINGS);
		Account maxiSavingsAccount = new Account(Account.MAXI_SAVINGS);
		oscar.openAccount(maxiSavingsAccount);
		oscar.openAccount(savingsAccount);

		savingsAccount.deposit(4000.0);
		oscar.transferBetweenAccounts(savingsAccount, maxiSavingsAccount, 500);
		
		assertEquals(3500.0, savingsAccount.sumTransactions(), 2);
		
		assertEquals(500.0, maxiSavingsAccount.sumTransactions(), 2);
	}

	@After
	public void tearDown() {
		oscar = null;
	}
}
