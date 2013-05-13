package by.bsu.bank.natural.action;

import by.bsu.bank.natural.entity.CreditCard;
import by.bsu.bank.natural.entity.Deposit;
import by.bsu.bank.natural.report.Report;

public class CreditCardActions extends CommonActions {
	private Report report = new Report();
	private DepositActions depositActions = new DepositActions();

	public boolean cardToCardTransfer(double sum, CreditCard from, CreditCard to) {
		double transferSum = 0;

		if (!from.getCurrency().equals(to.getCurrency())) {
			logger.info("Валюты разные, необходим перевод денег");
			transferSum = currencyExchange(sum, from, to);
		} else {
			transferSum = sum;
		}

		if (from.getSum() >= sum || from.isOverdraftAccess()) {
			from.setSum(from.getSum() - sum);
			to.setSum(to.getSum() + transferSum);
			logger.info("Перевод денег с карты на карту прошел успешно");
			return true;
		} else {
			logger.error(report.errorTransferReport(from));
			return false;
		}
	}

	public boolean cardToDepTransfer(double sum, CreditCard from, Deposit to) {
		double transferSum = 0;

		if (!from.getCurrency().equals(to.getCurrency())) {
			logger.info("Валюты разные, необходим перевод денег");
			transferSum = currencyExchange(sum, from, to);
		} else {
			transferSum = sum;
		}

		if (from.getSum() >= sum || from.isOverdraftAccess()) {
			from.setSum(from.getSum() - sum);
			to.setSum(depositActions.calculateDepositSum(to, to.getStartSum()) + transferSum);
			logger.info("Перевод денег с карты на депозит прошел успешно");
			return true;
		} else {
			logger.error(report.errorTransferReport(from));
			return false;
		}
	}
}
