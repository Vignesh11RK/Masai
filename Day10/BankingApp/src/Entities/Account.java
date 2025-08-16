package Entities;

import Exceptions.InsufficientBalance;
import Exceptions.LessValue;
import enums.AccountType;

import java.math.BigDecimal;
import java.util.Objects;


public abstract class Account {
    private String accountNo;
    private String customerId;
    private AccountType type;
    private BigDecimal balance;

    public Account() {

    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Account(String accountNo, String customerId, AccountType type, BigDecimal balance) {
        this.accountNo = accountNo;
        this.customerId = customerId;
        this.type = type;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNo='" + accountNo + '\'' +
                ", customerId='" + customerId + '\'' +
                ", type=" + type +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Account account)) return false;
        return Objects.equals(accountNo, account.accountNo) && Objects.equals(customerId, account.customerId) && type == account.type && Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNo, customerId, type, balance);
    }

    public void deposit(BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO)<=0){
           throw new LessValue("Deposit value should be greater than 0");
        }
        this.balance=this.balance.add(amount);
    }

    public void withdraw(BigDecimal amount){
// checking greater than balance for bigdecimal it is a bit different the operators and logic

        if(amount.compareTo(balance) > 0 ) {
           throw new InsufficientBalance("Insufficient balance");
        }
        this.balance=this.balance.subtract(amount);
    }

    public abstract BigDecimal getInterestRate();
    public abstract BigDecimal getMinimumBalance();

    public BigDecimal calculateInterestRate(){
        return balance.multiply(getInterestRate()).divide(new BigDecimal(100));
    }
}
