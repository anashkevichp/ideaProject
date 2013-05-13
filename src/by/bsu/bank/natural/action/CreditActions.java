package by.bsu.bank.natural.action;

import by.bsu.bank.natural.entity.Credit;

public class CreditActions extends CommonActions {

    public double calculateMonthlyEnroll(Credit credit) {
        double monthlyEnroll = calculateMonthlyDebt(credit) + credit.getSum() / credit.getTerm();
        return monthlyEnroll;
    }

    private double calculateMonthlyDebt(Credit credit) {
        double monthlyPercent = credit.getPercent() / 12.0;
        double monthlyDebt = credit.getDebt() * monthlyPercent;
        return monthlyDebt;
    }

    public double debtConversion(Credit credit) { // пересчёт долга
        double debt = credit.getSum();
        for (int i = 0; i < calculateContractPeriod(credit); i++) {
            debt += calculateMonthlyDebt(credit);
        }
        credit.setDebt(debt);
        return debt;
    }

    /*public void repayCredit(double sum, CreditCard creditCard, Credit credit) {
        double transferSum = 0;

        if (!creditCard.getCurrency().equals(credit.getCurrency())) {
//            logger.info("Валюты разные, необходим перевод денег");
            transferSum = commonActions.currencyExchange(sum, creditCard, credit);
        } else {
            transferSum = sum;
        }

        if (creditCard.getSum() >= sum || creditCard.isOverdraftAccess()) {
            creditCard.setSum(creditCard.getSum() - sum);
            double startDebt = credit.getDebt();
            double monthlyEnroll = calculateMonthlyEnroll(credit);
            credit.setDebt(startDebt - monthlyEnroll);
            to.setSum(calculateDepositSum(to, to.getStartSum()) + transferSum);
//            logger.info("Перевод денег с карты на депозит прошел успешно");
//            return report.cardToDepTransferReport(sum, from, to);
        } else {
//            logger.error(report.errorTransferReport(from));
//            return report.errorTransferReport(from);
        }
    }*/
    //  добавить погашение кредита
    //  добавить расчет месячного погашения
}
