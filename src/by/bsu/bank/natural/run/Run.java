package by.bsu.bank.natural.run;

import by.bsu.bank.natural.action.CommonActions;
import by.bsu.bank.natural.action.CreditCardActions;
import by.bsu.bank.natural.action.DepositActions;
import by.bsu.bank.natural.containers.CreditCardContainer;
import by.bsu.bank.natural.containers.CreditContainer;
import by.bsu.bank.natural.containers.DepositContainer;
import by.bsu.bank.natural.entity.Credit;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;

import by.bsu.bank.natural.report.Report;
import by.bsu.bank.natural.entity.CreditCard;
import by.bsu.bank.natural.entity.Deposit;

public class Run {

	public static void main(String[] args) {
		new DOMConfigurator().doConfigure("log/log4j.xml", LogManager.getLoggerRepository());

		CreditContainer credits = new CreditContainer();
		CreditCardContainer cards = new CreditCardContainer();
		DepositContainer deposits = new DepositContainer();

		deposits.add(new Deposit("input//Deposit.in"));
		credits.add(new Credit("input//Credit.in"));
		cards.add(new CreditCard("input//CreditCard.in"));
		cards.add(new CreditCard("input//CreditCard2.in"));

		CommonActions actions = new CommonActions();
		DepositActions depositActions = new DepositActions();
		CreditCardActions creditCardActions = new CreditCardActions();

		Report report = new Report();

		double profit = depositActions.calculateProfit(deposits.get(0));
		double period = actions.calculateContractPeriod(deposits.get(0));
		System.out.println(report.profitReport(profit, period, deposits.get(0)));

		boolean cardToDepTransferResult = creditCardActions.cardToDepTransfer(30, cards.get(0), deposits.get(0));
		System.out.println(report.cardToDepTransferReport(cardToDepTransferResult, 30, cards.get(0), deposits.get(0)));

		boolean cardToCardTransferResult = creditCardActions.cardToCardTransfer(30, cards.get(0), cards.get(1));
		System.out.println(report.cardToCardTransferReport(cardToCardTransferResult, 30, cards.get(0), cards.get(1)));
	}
}