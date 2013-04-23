package by.bsu.bank.natural.entity;

import by.bsu.bank.natural.action.Actions;
import by.bsu.bank.natural.creator.DepositCreator;

public class Deposit extends BankService {
	private boolean termExtension;
	private double startSum;
	private float monthlyEnroll;
	
	public Deposit(String fileName) {
		new DepositCreator(this, fileName);
	}

	public boolean isTermExtension() {
		return termExtension;
	}

	public void setTermExtension(boolean termExtension) {
		this.termExtension = termExtension;
	}

    @Override
	public double getSum() {
		Actions action = new Actions();
		return action.calculateDepositSum(this, startSum);
	}

	public double getStartSum() {
		return startSum;
	}

	public void setStartSum(double startSum) {
		this.startSum = startSum;
	}

	public float getMonthlyEnroll() {
		return monthlyEnroll;
	}

	public void setMonthlyEnroll(float monthlyEnroll) {
		this.monthlyEnroll = monthlyEnroll;
	}
}