package by.bsu.bank.natural.creator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import by.bsu.bank.natural.entity.CreditCard;
import by.bsu.bank.natural.entity.BankService.Currency;
import by.bsu.bank.natural.entity.CreditCard.CardType;

public class CreditCardCreator {

	public CreditCardCreator(CreditCard card, String fileName) {
		File file = new File(fileName);

		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				card.setBankName(scanner.next());
				card.setId(scanner.next());
				
				String cur = scanner.next().toUpperCase();	
				card.setCurrency(Currency.valueOf(cur));
				
				card.setSum(scanner.nextDouble());
				card.setPercent(scanner.nextFloat());
				card.setStatus(scanner.nextBoolean());
				card.setTerm(scanner.nextInt());
				card.setTermExtension(scanner.nextBoolean());
				card.setContractDate(scanner.next());
				
				String type = scanner.next().toUpperCase();
				card.setCardType(CardType.valueOf(type));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}