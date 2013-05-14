package by.bsu.bank.natural.action;

import by.bsu.bank.natural.entity.Credit;
import by.bsu.bank.natural.entity.CreditCard;

import java.util.ArrayList;

public class CreditActions extends CommonActions {

	public double calculateMonthlyEnroll(Credit credit) {
		logger.info("Расчет ежемесячных выплат по кредиту...");
		double percent = credit.getPercent() / (12.0 * 100.0);
		double sum = credit.getSum();
		double term = credit.getTerm();
		double monthlyEnroll = sum * (percent + percent / (Math.pow(1 + percent, term) - 1));
		logger.debug("Итого: " + monthlyEnroll);
		return monthlyEnroll;
	}

	public void calculatePayments(double monthlyEnroll, Credit credit) {
		ArrayList<Double> basicPayments = new ArrayList<Double>();
		ArrayList<Double> percentPayments = new ArrayList<Double>();
		ArrayList<Double> debt = new ArrayList<Double>();
		double percent = credit.getPercent() / (12.0 * 100.0);
		double paymentsMultiplier = 1 + percent;

		double firstBasicPayment = monthlyEnroll - percent * credit.getSum();
		basicPayments.add(0, 0.0);
		percentPayments.add(0, 0.0);
		debt.add(0, credit.getDebt());

		basicPayments.add(1, firstBasicPayment);
		percentPayments.add(1, monthlyEnroll - firstBasicPayment);
		debt.add(1, debt.get(0) - monthlyEnroll + percentPayments.get(1));

		for (int i = 2; i <= credit.getTerm(); i++) {
			double basicPayment = basicPayments.get(i-1) * paymentsMultiplier;
			basicPayments.add(i, basicPayment);
			percentPayments.add(i, monthlyEnroll - basicPayment);
			debt.add(i, debt.get(i-1) - monthlyEnroll + percentPayments.get(i));
			System.out.println(basicPayments.get(i) + "\t" + percentPayments.get(i) + "\t" + debt.get(i));
		}
	}

//	public double calculateDebt();

	public boolean repayCredit(CreditCard card, Credit credit) {
		double sum = calculateMonthlyEnroll(credit);
		double transferSum = 0;

		if (!card.getCurrency().equals(credit.getCurrency())) {
            logger.info("Валюты разные, необходим перевод денег");
			transferSum = currencyExchange(sum, card, credit); // переделать перевод денег. Переводить только на карте!
		} else {
			transferSum = sum;
		}

		if (card.getSum() >= sum || card.isOverdraftAccess()) {
			card.setSum(card.getSum() - sum);
			return true;
		} else {
            return false;
		}
	}
}