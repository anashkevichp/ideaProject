package by.bsu.bank.natural.run;

import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;

import by.bsu.bank.natural.action.Actions;
import by.bsu.bank.natural.creator.Containers;
import by.bsu.bank.natural.entity.CreditCard;
import by.bsu.bank.natural.entity.Deposit;

public class Run {
	
	public static void main(String[] args) {
		new DOMConfigurator().doConfigure("log/log4j.xml", LogManager.getLoggerRepository());
		
		Containers c = new Containers();
		
		c.addToDeposits(new Deposit("input//Deposit.in"));
		c.addToCreditCards(new CreditCard("input//CreditCard.in"));
		c.addToCreditCards(new CreditCard("input//CreditCard2.in"));
		
		Actions action = new Actions();
		System.out.println(action.calculateProfit(c.getDeposits(0)));
		System.out.println(action.cardToDepTransfer(30, c.getCreditCards(0), c.getDeposits(0)));
		System.out.println(action.cardToCardTransfer(30, c.getCreditCards(0), c.getCreditCards(1)));
		
	}
}