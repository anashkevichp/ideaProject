package by.bsu.bank.natural.action;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import by.bsu.bank.natural.entity.BankService;
import by.bsu.bank.natural.entity.CreditCard;
import by.bsu.bank.natural.entity.Deposit;
import by.bsu.bank.natural.report.Report;
import by.bsu.bank.natural.run.Run;

public class Actions {
	final static Logger logger = Logger.getLogger(Run.class);
	Report report = new Report();

	private Date stringToDate(BankService op) {
		Date date = null;
		try {
			date = new SimpleDateFormat("dd.MM.yyyy").parse(op
					.getContractDate());
		} catch (ParseException e) {
			logger.error("Ошибка перевода даты");
			e.printStackTrace();
		}
		return date;
	}

	private double getPeriod(BankService op) {
		Calendar calendar = new GregorianCalendar();
		long contractDate = stringToDate(op).getTime();
		long actualDate = calendar.getTimeInMillis();
		long days = (actualDate - contractDate) / (24 * 60 * 60 * 1000);
		double months = days / 30;
		return months;
	}

	public double calculateDepositSum(Deposit dep, double depositSum) {
		double percent = dep.getPercent() / 12.0;
		for (int i = 0; i < getPeriod(dep); i++) {
			depositSum += (depositSum + dep.getMonthlyEnroll()) * percent
					/ 100.0 + dep.getMonthlyEnroll();
		}
		logger.debug("Актуальная сумма депозита: " + depositSum);
		return depositSum;
	}

	public String calculateProfit(Deposit dep) {
		double profit = dep.getSum() - dep.getStartSum()
				- dep.getMonthlyEnroll() * getPeriod(dep);
		logger.debug("Чистая прибыль по депозиту за период: " + getPeriod(dep) + " месяца(ев) " + profit);
		return report.profitReport(profit, getPeriod(dep), dep);
	}

	private double calculateCurrencyCoef(CreditCard from, BankService to) {
		double eurToUsd = 0;
		double eurToByr = 0;
		double eurToRub = 0;
		double currencyCoef = 1;

		String fileName = "input//CurrencyRates.in";
		File file = new File(fileName);
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				eurToUsd = scanner.nextDouble();
				eurToByr = scanner.nextDouble();
				eurToRub = scanner.nextDouble();
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			logger.error("Ошибка чтения с файла коэффициентов конвертации");
			e.printStackTrace();
		}

		switch (from.getCurrency()) {
		case EUR:
			if ("USD".equals(to.getCurrency().name())) {
				currencyCoef = eurToUsd;
			}
			if ("BYR".equals(to.getCurrency().name())) {
				currencyCoef = eurToByr;
			}
			if ("RUB".equals(to.getCurrency().name())) {
				currencyCoef = eurToRub;
			}
			break;
		case USD:
			if ("EUR".equals(to.getCurrency().name())) {
				currencyCoef = 1 / eurToUsd;
			}
			if ("BYR".equals(to.getCurrency().name())) {
				currencyCoef = eurToByr / eurToUsd;
			}
			if ("RUB".equals(to.getCurrency().name())) {
				currencyCoef = eurToRub / eurToUsd;
			}
			break;
		case BYR:
			if ("EUR".equals(to.getCurrency().name())) {
				currencyCoef = 1 / eurToByr;
			}
			if ("USD".equals(to.getCurrency().name())) {
				currencyCoef = eurToUsd / eurToByr;
			}
			if ("RUB".equals(to.getCurrency().name())) {
				currencyCoef = eurToRub / eurToByr;
			}
			break;
		case RUB:
			if ("EUR".equals(to.getCurrency().name())) {
				currencyCoef = 1 / eurToRub;
			}
			if ("USD".equals(to.getCurrency().name())) {
				currencyCoef = eurToByr / eurToUsd;
			}
			if ("BYR".equals(to.getCurrency().name())) {
				currencyCoef = eurToRub / eurToUsd;
			}
			break;
		default:
			logger.error("Ошибка расчета коэффициента конвертации");
			throw new IllegalArgumentException("Плохой входной параметр "
					+ from.getCurrency());
		}
		return currencyCoef;
	}

	private double currencyExchange(double sum, CreditCard from, BankService to) {
		double currencyCoef = calculateCurrencyCoef(from, to);
		logger.debug("Итог конвертации: " + sum*currencyCoef + to.getCurrency().name());
		return sum * currencyCoef;
	}

	public String cardToCardTransfer(double sum, CreditCard from, CreditCard to) {
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
			return report.cardToCardTransferReport(sum, from, to);
		} else {
			logger.error(report.errorTransferReport(from));
			return report.errorTransferReport(from);
		}
	}

	public String cardToDepTransfer(double sum, CreditCard from, Deposit to) {
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
			logger.info("Перевод денег с карты на депозит прошел успешно");
			return report.cardToDepTransferReport(sum, from, to);
		} else {
			logger.error(report.errorTransferReport(from));
			return report.errorTransferReport(from);
		}
	}
	
	// добавить погашение кредита
	//	добавить расчет месячного погашения
}