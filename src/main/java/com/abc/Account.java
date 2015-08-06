package com.abc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Account {

	public static final int CHECKING = 0;
	public static final int SAVINGS = 1;
	public static final int MAXI_SAVINGS = 2;

	private final int accountType;
	private List<Transaction> transactions;

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Account(int accountType) {
		this.accountType = accountType;
		this.transactions = new ArrayList<Transaction>();
	}

	public void deposit(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		} else {
			transactions.add(new Transaction(amount));
		}
	}

	public void withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		} else {
			transactions.add(new Transaction(-amount));
		}
	}

	public double interestEarned() {
		double amount = sumTransactions();
		switch (accountType) {
		case SAVINGS:
			if (amount <= 1000)
				return amount * 0.001 / 365;
			else
				return 1 + ((amount - 1000) * 0.002 / 365);
		case MAXI_SAVINGS:
			if (!checkIfWithdrawalsExistBeforeNDays(10)) {
				return amount * 0.05 / 365;
			} else {
				return amount * 0.001 / 365;
			}
		default:
			return amount * 0.001;
		}
	}

	public double sumTransactions() {
		double amount = 0.0;
		for (Transaction t : transactions)
			amount += t.amount;
		return amount;
	}

	private boolean checkIfWithdrawalsExistBeforeNDays(int numDays) {
		Calendar calendar;
		for (Transaction t : transactions) {
			calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, -10);
			if (t.getTransactionDate().compareTo(calendar.getTime()) > 0
					&& t.amount < 0) {
				return true;
			}
		}
		return false;
	}

	public int getAccountType() {
		return accountType;
	}

}
