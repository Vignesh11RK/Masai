package Entities;

import enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {

    private String TransactionId;
    private BigDecimal amount;
    private String accountNo;
    private LocalDateTime timestamp;
    private TransactionType type;
    private String toAccountNo;

//    public Transaction(String accountNo, BigDecimal amount, LocalDateTime now, String transactionsId, TransactionType transactionType) {
//    }
//
//    public Transaction(String toAccountNo) {
//        this.toAccountNo = toAccountNo;
//    }
//
//    public Transaction(String fromAccountNo, BigDecimal amount, LocalDateTime now, String transactionId, TransactionType transactionType, String toAccountNoStr) {
//    }
//
//    public Transaction(String accountNo, TransactionType transactionType, BigDecimal bigDecimal) {
//    }

    public void Transaction(){
    }

    public Transaction(String accountNo, TransactionType type, BigDecimal amount) {
        this.accountNo = accountNo;
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
        this.TransactionId = "TXN" + System.currentTimeMillis();
    }

    public Transaction(String accountNo, BigDecimal amount, LocalDateTime timestamp, String transactionId, TransactionType type, String toAccountNo) {
        this.accountNo = accountNo;
        this.amount = amount;
        this.timestamp = timestamp;
        this.TransactionId = transactionId;
        this.type = type;
        this.toAccountNo = toAccountNo;
    }

    public Transaction(String accountNo, BigDecimal amount, LocalDateTime timestamp, String transactionId, TransactionType type) {
        this.accountNo = accountNo;
        this.amount = amount;
        this.timestamp = timestamp;
        this.TransactionId = transactionId;
        this.type = type;
        this.toAccountNo = null; // no toAccount for deposit or withdraw
    }






    public String getToAccountNo() {
        return toAccountNo;
    }

    public void setToAccountNo(String toAccountNo) {
        this.toAccountNo = toAccountNo;
    }

    public String getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(String transactionId) {
        TransactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }




    // to string
    @Override
    public String toString() {
        return "Transaction{" +
                "TransactionId='" + TransactionId + '\'' +
                ", amount=" + amount +
                ", accountNo='" + accountNo + '\'' +
                ", timestamp=" + timestamp +
                ", type=" + type +
                '}';
    }


    //hasdcode generation directly by ide
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Transaction that)) return false;
        return Objects.equals(TransactionId, that.TransactionId) && Objects.equals(amount, that.amount) && Objects.equals(accountNo, that.accountNo) && Objects.equals(timestamp, that.timestamp) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(TransactionId, amount, accountNo, timestamp, type);
    }


    public void add(Transaction transaction) {
    }
}
