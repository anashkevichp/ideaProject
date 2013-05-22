package by.bsu.bank.operations.containers;

import by.bsu.bank.operations.entity.Credit;
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
