package by.bsu.bank.operations.run;

import by.bsu.bank.operations.action.CreditActions;
import by.bsu.bank.operations.containers.CreditCardContainer;
import by.bsu.bank.operations.containers.CreditContainer;
import by.bsu.bank.operations.containers.DepositContainer;
import by.bsu.bank.operations.entity.Credit;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;

import by.bsu.bank.operations.report.Report;
import by.bsu.bank.operations.entity.CreditCard;
import by.bsu.bank.operations.entity.Deposit;

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

//		CommonActions actions = new CommonActions();
//		DepositActions depositActions = new DepositActions();
		CreditActions creditActions = new CreditActions();
//		CreditCardActions creditCardActions = new CreditCardActions();

		Report report = new Report();

		double monthlyEnroll = creditActions.calculateMonthlyEnroll(credits.get(0));
//		System.out.println(report.monthlyEnrollCreditReport(monthlyEnroll, credits.get(0)));

		double term = creditActions.calculateContractPeriod(credits.get(0));
		System.out.println(credits.get(0).isCurrentMonthPayment((int) term)); // добавить в репорты проверку платежа, затем погашение кредита


/*
//		creditActions.calculatePayments(monthlyEnroll, credits.get(0));

		double profit = depositActions.calculateProfit(deposits.get(0));
		double period = actions.calculateContractPeriod(deposits.get(0));
//		System.out.println(report.profitReport(profit, period, deposits.get(0)));

		boolean cardToDepTransferResult = creditCardActions.cardToDepTransfer(30, cards.get(0), deposits.get(0));
//		System.out.println(report.cardToDepTransferReport(cardToDepTransferResult, 30, cards.get(0), deposits.get(0)));

		boolean cardToCardTransferResult = creditCardActions.cardToCardTransfer(30, cards.get(0), cards.get(1));
//		System.out.println(report.cardToCardTransferReport(cardToCardTransferResult, 30, cards.get(0), cards.get(1)));
*/
	}
}