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

    public void Transaction(){
    }

    public Transaction(String transactionId, BigDecimal amount, String accountNo, LocalDateTime timestamp, TransactionType type) {
        TransactionId = transactionId;
        this.amount = amount;
        this.accountNo = accountNo;
        this.timestamp = timestamp;
        this.type = type;
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


}
