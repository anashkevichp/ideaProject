package by.bsu.bank.natural.entity;

import by.bsu.bank.natural.creator.CreditCardCreator;

public class CreditCard extends BankService {

	public enum CardType {
		DEBIT, CREDIT
	}

	private CardType cardType;
	private double sum;
	private boolean termExtension;
	private boolean salaryCard;
	private boolean overdraftAccess;

	public CreditCard(String fileName) {
		new CreditCardCreator(this, fileName);
	}

	public boolean isTermExtension() {
		return termExtension;
	}

	public void setTermExtension(boolean termExtension) {
		this.termExtension = termExtension;
	}

	public boolean isSalaryCard() {
		return salaryCard;
	}

	public void setSalaryCard(boolean salaryCard) {
		this.salaryCard = salaryCard;
	}

	public boolean isOverdraftAccess() {
		return overdraftAccess;
	}

	public void setOverdraftAccess(boolean overdraftAccess) {
		this.overdraftAccess = overdraftAccess;
	}

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}
}