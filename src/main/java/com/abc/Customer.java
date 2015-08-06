package com.abc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Customer {
	private String name;
	private List<Account> accounts;

	public Customer(String name) {
		this.name = name;
		this.accounts = new ArrayList<Account>();
	}

	public String getName() {
		return name;
	}

	public void openAccount(Account account) {
		accounts.add(account);
	}

	public int getNumberOfAccounts() {
		return accounts.size();
	}

	public double totalInterestEarned() {
		double total = 0;
		for (Account a : accounts)
			total += a.interestEarned();
		return total;
	}

	public void transferBetweenAccounts(Account srcAccount,
			Account destAccount, double amount) {
		srcAccount.withdraw(amount);
		destAccount.deposit(amount);
	}

	public String getStatement() {
		// use stringbuilder to avoid memory leaks with string
		StringBuilder statement = new StringBuilder("Statement for " + name
				+ "\n");
		double total = 0.0;
		for (Account a : accounts) {
			statement.append("\n");
			statement.append(statementForAccount(a));
			statement.append("\n");
			total += a.sumTransactions();
		}
		statement.append("\nTotal In All Accounts " + toDollars(total));
		return statement.toString();
	}

	private String statementForAccount(Account a) {
		StringBuilder s = new StringBuilder();

		// Translate to pretty account type
		switch (a.getAccountType()) {
		case Account.CHECKING:
			s.append("Checking Account\n");
			break;
		case Account.SAVINGS:
			s.append("Savings Account\n");
			break;
		case Account.MAXI_SAVINGS:
			s.append("Maxi Savings Account\n");
			break;
		}

		// Now total up all the transactions
		double total = 0.0;
		for (Transaction t : a.getTransactions()) {
			s.append("  " + (t.amount < 0 ? "withdrawal" : "deposit") + " "
					+ toDollars(t.amount) + "\n");
			total += t.amount;
		}
		s.append("Total " + toDollars(total));
		return s.toString();
	}

	private String toDollars(double d) {
		return String.format("$%,.2f", abs(d));
	}
}
