package by.bsu.bank.natural.containers;

import by.bsu.bank.natural.entity.Deposit;
import java.util.ArrayList;

public class DepositContainer {
    private ArrayList<Deposit> deposits = new ArrayList<Deposit>();

    public Deposit get(int index) {
        return deposits.get(index);
    }

    public void add(Deposit deposit) {
        deposits.add(deposit);
    }
}
