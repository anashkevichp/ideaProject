package by.bsu.bank.operations.creator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import by.bsu.bank.operations.entity.Credit;
import by.bsu.bank.operations.entity.BankService.Currency;

public class CreditCreator {

	public CreditCreator(Credit credit, String fileName) {
		File file = new File(fileName);

		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				credit.setBankName(scanner.next());
				credit.setId(scanner.next());

				String cur = scanner.next().toUpperCase();	
				credit.setCurrency(Currency.valueOf(cur));

				double sum = scanner.nextDouble();
				credit.setSum(sum);
				credit.setDebt(sum);

				credit.setPercent(scanner.nextDouble());
				credit.setStatus(scanner.nextBoolean());
				credit.setTerm(scanner.nextInt());
				credit.setPaidAmount(scanner.nextDouble());       // viplacheno
				credit.setContractDate(scanner.next());
				credit.setCreditPurpose(scanner.next());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
