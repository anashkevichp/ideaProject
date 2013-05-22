package by.bsu.bank.operations.creator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import by.bsu.bank.operations.entity.Deposit;
import by.bsu.bank.operations.entity.BankService.Currency;

public class DepositCreator {

	public DepositCreator(Deposit dep, String fileName) {

		File file = new File(fileName);

		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				dep.setBankName(scanner.next());
				dep.setId(scanner.next());
				dep.setMonthlyEnroll(scanner.nextFloat());
				dep.setPercent(scanner.nextFloat());
				dep.setStatus(scanner.nextBoolean());
				dep.setStartSum(scanner.nextDouble());
				dep.setSum(scanner.nextDouble());
				dep.setTerm(scanner.nextInt());

				String cur = scanner.next().toUpperCase();
				dep.setCurrency(Currency.valueOf(cur));

				dep.setTermExtension(scanner.nextBoolean());
				dep.setContractDate(scanner.next());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
