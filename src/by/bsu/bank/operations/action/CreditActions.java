package by.bsu.bank.operations.action;

import by.bsu.bank.operations.entity.Credit;
import by.bsu.bank.operations.entity.CreditCard;

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
		ArrayList<Double> debt = new ArrayList<Double>();
		double percent = credit.getPercent() / (12.0 * 100.0);
		final double paymentsMultiplier = 1 + percent;

		double currentDebt = credit.getDebt();
		debt.add(currentDebt);

		double basicPayment = monthlyEnroll - percent * credit.getSum();
		currentDebt -= basicPayment;
		debt.add(currentDebt);

		for (int i = 2; i <= credit.getTerm(); i++) {
			basicPayment *= paymentsMultiplier;
			currentDebt -= basicPayment;
			debt.add(i, currentDebt);
			System.out.println(debt.get(i));
		}
	}

	public boolean repayCredit(CreditCard card, Credit credit) {
		double sum = calculateMonthlyEnroll(credit);

		if (!card.getCurrency().equals(credit.getCurrency())) {
			logger.info("Валюты разные, необходим перевод денег");
			sum = currencyExchange(sum, credit, card);
		}

		int term = credit.getTerm();
		if (credit.isCurrentMonthPayment(term)) {
			logger.error("Платёж в этом месяце уже был произведён");
			return false;
		}

		if (card.getSum() >= sum || card.isOverdraftAccess()) {
			credit.setCurrentMonthPayment(term, true);
			card.setSum(card.getSum() - sum);
			credit.setPaidAmount(credit.getPaidAmount() + calculateMonthlyEnroll(credit));
			return true;
		} else {
			logger.error("На платёжной карте недостаточно средств");
			return false;
		}
	}
}
