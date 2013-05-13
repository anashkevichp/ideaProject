package by.bsu.bank.natural.containers;

import by.bsu.bank.natural.entity.Credit;
import java.util.ArrayList;

public class CreditContainer {
	private ArrayList<Credit> credits = new ArrayList<Credit>();

	public Credit get(int index) {
		return credits.get(index);
	}

	public void add(Credit credit) {
		credits.add(credit);
	}
}
