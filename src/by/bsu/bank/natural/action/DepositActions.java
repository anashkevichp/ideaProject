package by.bsu.bank.natural.action;

import by.bsu.bank.natural.entity.Deposit;

public class DepositActions extends CommonActions {

    public double calculateDepositSum(Deposit dep, double depositSum) {
        double percent = dep.getPercent() / 12.0;
        for (int i = 0; i < calculateContractPeriod(dep); i++) {
            depositSum += (depositSum + dep.getMonthlyEnroll())
                    * percent / 100.0 + dep.getMonthlyEnroll();
        }
        logger.debug("Актуальная сумма депозита: " + depositSum);
        return depositSum;
    }

    public double calculateProfit(Deposit dep) {
        double profit = calculateDepositSum(dep, dep.getStartSum()) - dep.getStartSum()
                - dep.getMonthlyEnroll() * calculateContractPeriod(dep);
        logger.debug("Чистая прибыль по депозиту за период: "
        + calculateContractPeriod(dep) + " месяца(ев) " + profit);
        return profit;
    }
}
