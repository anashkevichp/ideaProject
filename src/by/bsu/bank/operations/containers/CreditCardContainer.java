package by.bsu.bank.operations.containers;

import by.bsu.bank.operations.entity.CreditCard;
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
