package by.bsu.bank.natural.containers;

import by.bsu.bank.natural.entity.CreditCard;
import java.util.ArrayList;

public class CreditCardContainer {
	private ArrayList<CreditCard> cards = new ArrayList<CreditCard>();

	public CreditCard get(int index) {
		return cards.get(index);
	}

	public void add(CreditCard card) {
		cards.add(card);
	}
}
