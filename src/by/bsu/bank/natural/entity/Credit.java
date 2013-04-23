package by.bsu.bank.natural.entity;

import by.bsu.bank.natural.creator.CreditCreator;

public class Credit extends BankService {
	private String type;
	private float creditAmount;
	private float paidAmount;
	private float monthlyPayment;

	public Credit(String fileName) {
		new CreditCreator(this, fileName);
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(float creditAmount) {
		this.creditAmount = creditAmount;
	}

	public float getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(float paidAmount) {
		this.paidAmount = paidAmount;
	}

	public float getMonthlyPayment() {
		return monthlyPayment;
	}

	public void setMonthlyPayment(float monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}
}