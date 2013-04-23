package by.bsu.bank.natural.creator;

import java.util.ArrayList;

import by.bsu.bank.natural.entity.Credit;
import by.bsu.bank.natural.entity.CreditCard;
import by.bsu.bank.natural.entity.Deposit;

public class Containers {
	private ArrayList<Deposit> deposits = new ArrayList<Deposit>();
	private ArrayList<CreditCard> cards = new ArrayList<CreditCard>();
	private ArrayList<Credit> credits = new ArrayList<Credit>();
	
	public Deposit getDeposits(int index) {
		return deposits.get(index);
	}

	public void addToDeposits(Deposit deposit) {
		deposits.add(deposit);
	}
	
	public CreditCard getCreditCards(int index) {
		return cards.get(index);
	}

	public void addToCreditCards(CreditCard card) {
		cards.add(card);
	}

	public Credit getCredits(int index) {
		return credits.get(index);
	}

	public void addToCredits(Credit credit) {
		credits.add(credit);
	}
}