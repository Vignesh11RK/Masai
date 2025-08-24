package Entities;

import enums.AccountType;

import java.math.BigDecimal;

public class CurrentAccount extends Account {

    public static final BigDecimal interest_rate=new BigDecimal(4);
    public static final BigDecimal minimum_balance=new BigDecimal(4);

    public CurrentAccount(){
        super();
        setType(AccountType.CURRENT);
    }

    public CurrentAccount(String accountNo, String customerId, AccountType type, BigDecimal balance) {
        super(accountNo, customerId, AccountType.CURRENT, balance);
    }

    public CurrentAccount(String accountNo, String cutomerId, BigDecimal initialBalance) {
    }

    @Override
    public BigDecimal getInterestRate() {
        return null;
    }

    @Override
    public BigDecimal getMinimumBalance() {
        return null;
    }

    // to string left

    @Override
    public String toString() {
        return "Account{" +
                "accountNo='" + getAccountNo() + '\'' +
                ", customerId='" + getCustomerId() + '\'' +
                ", type=" + getType() +
                ", balance=" + getBalance() +
                '}';
    }


}
