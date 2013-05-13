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
import by.bsu.bank.natural.entity.Credit;
import by.bsu.bank.natural.entity.Deposit;
import by.bsu.bank.natural.report.Report;
import by.bsu.bank.natural.run.Run;

public class CommonActions {
	final static Logger logger = Logger.getLogger(Run.class);

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

	public double calculateContractPeriod(BankService op) {
		Calendar calendar = new GregorianCalendar();
		long contractDate = stringToDate(op).getTime();
		long actualDate = calendar.getTimeInMillis();
		long days = (actualDate - contractDate) / (24 * 60 * 60 * 1000);
		double months = days / 30;
		return months;
	}

	protected double calculateCurrencyCoef(CreditCard from, BankService to) {
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
			currencyCoef = calculateEurCurrencyCoef(to, eurToUsd, eurToByr, eurToRub);
			break;
		case USD:
            currencyCoef = calculateUsdCurrencyCoef(to, eurToUsd, eurToByr, eurToRub);
			break;
		case BYR:
            currencyCoef = calculateByrCurrencyCoef(to, eurToUsd, eurToByr, eurToRub);
			break;
		case RUB:
            currencyCoef = calculateRubCurrencyCoef(to, eurToUsd, eurToByr, eurToRub);
			break;
		default:
			logger.error("Ошибка расчета коэффициента конвертации");
			throw new IllegalArgumentException("Плохой входной параметр "
					+ from.getCurrency());
		}
		return currencyCoef;
	}

    private double calculateEurCurrencyCoef(BankService to, double eurToUsd, double eurToByr, double eurToRub) {
        double eurCurrencyCoef = 0;
        if ("USD".equals(to.getCurrency().name())) {
            eurCurrencyCoef = eurToUsd;
        }
        if ("BYR".equals(to.getCurrency().name())) {
            eurCurrencyCoef = eurToByr;
        }
        if ("RUB".equals(to.getCurrency().name())) {
            eurCurrencyCoef = eurToRub;
        }
        return eurCurrencyCoef;
    }

    private double calculateUsdCurrencyCoef(BankService to, double eurToUsd, double eurToByr, double eurToRub) {
        double usdCurrencyCoef = 0;
        if ("EUR".equals(to.getCurrency().name())) {
            usdCurrencyCoef = 1 / eurToUsd;
        }
        if ("BYR".equals(to.getCurrency().name())) {
            usdCurrencyCoef = eurToByr / eurToUsd;
        }
        if ("RUB".equals(to.getCurrency().name())) {
            usdCurrencyCoef = eurToRub / eurToUsd;
        }
        return usdCurrencyCoef;
    }

    private double calculateByrCurrencyCoef(BankService to, double eurToUsd, double eurToByr, double eurToRub) {
        double byrCurrencyCoef = 0;
        if ("EUR".equals(to.getCurrency().name())) {
            byrCurrencyCoef = 1 / eurToByr;
        }
        if ("USD".equals(to.getCurrency().name())) {
            byrCurrencyCoef = eurToUsd / eurToByr;
        }
        if ("RUB".equals(to.getCurrency().name())) {
            byrCurrencyCoef = eurToRub / eurToByr;
        }
        return byrCurrencyCoef;
    }

    private double calculateRubCurrencyCoef(BankService to, double eurToUsd, double eurToByr, double eurToRub) {
        double rubCurrencyCoef = 0;
        if ("EUR".equals(to.getCurrency().name())) {
            rubCurrencyCoef = 1 / eurToRub;
        }
        if ("USD".equals(to.getCurrency().name())) {
            rubCurrencyCoef = eurToByr / eurToUsd;
        }
        if ("BYR".equals(to.getCurrency().name())) {
            rubCurrencyCoef = eurToRub / eurToUsd;
        }
        return rubCurrencyCoef;
    }

	protected double currencyExchange(double sum, CreditCard from, BankService to) {
		double currencyCoef = calculateCurrencyCoef(from, to);
		logger.debug("Итог конвертации: " + sum*currencyCoef + to.getCurrency().name());
		return sum * currencyCoef;
	}
}