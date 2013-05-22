package by.bsu.bank.operations.entity;

import by.bsu.bank.operations.creator.CreditCreator;

import java.util.ArrayList;

public class Credit extends BankService {
	private String creditPurpose;
	private double debt;
	private double paidAmount; // оплаченная сумма
	private ArrayList<Boolean> currentMonthPayment = new ArrayList<Boolean>();

	public Credit(String fileName) {
		new CreditCreator(this, fileName);
		for (int i = 0; i < getTerm(); i++) {
			currentMonthPayment.add(false);
		}
	}

	public Boolean isCurrentMonthPayment(int index) {
		return currentMonthPayment.get(index);
	}

	public void setCurrentMonthPayment(int index, Boolean payment) {
		currentMonthPayment.add(index, payment);
	}

	public String getCreditPurpose() {
		return creditPurpose;
	}

	public void setCreditPurpose(String creditPurpose) {
		this.creditPurpose = creditPurpose;
	}

	public double getDebt() {
		return debt;
	}

	public void setDebt(double debt) {
		this.debt = debt;
	}

	public double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}
}