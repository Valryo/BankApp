package butterfly.blue.bankapp.sqlite.model;

import java.util.Date;

/**
 * Created by Valryo on 13/10/2017.
 */

public class Transaction {
    int id;
    int CreatedAt;
    int ModifiedAt;
    int Amount;
    int TransactionDate;
    boolean IsIncome;
    int AccountId;

    public Transaction(){

    }

    public Transaction(int id, int createdAt, int modifiedAt, int amount, int transactionDate, boolean isIncome, int accountId) {
        this.id = id;
        this.CreatedAt = createdAt;
        this.ModifiedAt = modifiedAt;
        this.Amount = amount;
        this.TransactionDate = transactionDate;
        this.IsIncome = isIncome;
        this.AccountId = accountId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(int createdAt) {
        CreatedAt = createdAt;
    }

    public int getModifiedAt() {
        return ModifiedAt;
    }

    public void setModifiedAt(int modifiedAt) {
        ModifiedAt = modifiedAt;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public int getTransactionDate() {
        return TransactionDate;
    }

    public void setTransactionDate(int transactionDate) {
        TransactionDate = transactionDate;
    }

    public boolean isIncome() {
        return IsIncome;
    }

    public void setIncome(boolean income) {
        IsIncome = income;
    }

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int accountId) {
        AccountId = accountId;
    }
}
