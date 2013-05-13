package by.bsu.bank.natural.entity;

import by.bsu.bank.natural.creator.CreditCreator;

public class Credit extends BankService {
	private String creditPurpose;
	private double debt;
	private double paidAmount; // оплаченная сумма
	private double monthlyPayment;   // перевести в CommonActions

	public Credit(String fileName) {
		new CreditCreator(this, fileName);
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

	public double getMonthlyPayment() {
		return monthlyPayment;
	}

	public void setMonthlyPayment(double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}
}