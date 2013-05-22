package by.bsu.bank.operations.entity;

import by.bsu.bank.operations.action.DepositActions;
import by.bsu.bank.operations.creator.DepositCreator;

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
		DepositActions depositAction = new DepositActions();
		return depositAction.calculateDepositSum(this, startSum);
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