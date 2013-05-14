package by.bsu.bank.natural.report;

import by.bsu.bank.natural.entity.Credit;
import by.bsu.bank.natural.entity.CreditCard;
import by.bsu.bank.natural.entity.Deposit;

public class Report {

	public String cardToCardTransferReport(boolean result, double sum, CreditCard from, CreditCard to) {
		if (result == true) {
			return cardToCardTransferReport(sum, from, to);
		} else {
			return errorTransferReport(from);
		}
	}

	public String cardToDepTransferReport(boolean result, double sum, CreditCard from, Deposit to) {
		if (result == true) {
			return cardToDepTransferReport(sum, from, to);
		} else {
			return errorTransferReport(from);
		}
	}

	private String cardToCardTransferReport(double sum, CreditCard from, CreditCard to) {
		return "Transfer " + sum + " " + from.getCurrency() + " from credit card #"
				+ from.getId() + " to credit card #" + to.getId()
				+ " was successful.\n"
				+ "Balance/debt from credit card #"
				+ from.getId() + ": " + from.getSum() + " "
				+ from.getCurrency().name() + "\n"
				+ "Balance/debt from credit card #"
				+ to.getId() + ": " + to.getSum() + " "
				+ to.getCurrency().name() + "\n";
	}

	private String cardToDepTransferReport(double sum, CreditCard from, Deposit to) {
		return "Transfer " + sum + " " + from.getCurrency() + " from credit card #"
				+ from.getId() + " to deposit #" + to.getId()
				+ " was successful.\n"
				+ "Balance/debt from credit card #"
				+ from.getId() + ": " + from.getSum() + " "
				+ from.getCurrency().name() + "\n"
				+ "Balance from deposit #"
				+ to.getId() + ": " + to.getSum() + " "
				+ to.getCurrency().name() + "\n";
	}

	public String profitReport(double profit, double period, Deposit dep) {
		return "Net profit of the deposit #"
				+ dep.getId() + " for the period "
				+ period + " months : " + profit + " "
				+ dep.getCurrency().name() + "\n";
	}

	public String monthlyEnrollCreditReport(double monthlyEnroll, Credit credit) {
		return "Monthly payments on the credit #" + credit.getId()
				+ " should be: " + monthlyEnroll + " "
				+ credit.getCurrency().name();
	}

	public String errorTransferReport(CreditCard from) {
		return "Ошибка перевода денег с карты #" + from.getId(); 
	}
}
