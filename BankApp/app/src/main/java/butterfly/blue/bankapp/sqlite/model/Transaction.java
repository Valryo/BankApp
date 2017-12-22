package butterfly.blue.bankapp.sqlite.model;

import java.util.Date;

/**
 * Created by Valryo on 13/10/2017.
 */

public class Transaction {
    private long id;
    private long CreatedAt;
    private long ModifiedAt;
    private float Amount;
    private long TransactionDate;
    private boolean IsIncome;
    private long AccountId;

    public Transaction(){

    }

    public Transaction(float amount, long transactionDate, boolean isIncome, long accountId) {
        this.Amount = amount;
        this.TransactionDate = transactionDate;
        this.IsIncome = isIncome;
        this.AccountId = accountId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(long createdAt) {
        CreatedAt = createdAt;
    }

    public long getModifiedAt() {
        return ModifiedAt;
    }

    public void setModifiedAt(long modifiedAt) {
        ModifiedAt = modifiedAt;
    }

    public float getAmount() {
        return Amount;
    }

    public void setAmount(float amount) {
        Amount = amount;
    }

    public long getTransactionDate() {
        return TransactionDate;
    }

    public void setTransactionDate(long transactionDate) {
        TransactionDate = transactionDate;
    }

    public boolean isIncome() {
        return IsIncome;
    }

    public void setIncome(boolean income) {
        IsIncome = income;
    }

    public long getAccountId() {
        return AccountId;
    }

    public void setAccountId(long accountId) {
        AccountId = accountId;
    }

    @Override
    public String toString(){
        return id + " | AccountID: " + AccountId + " - Amount: " + (this.IsIncome ? "+" : "-") + Amount + " - " + new Date(TransactionDate*1000L);
    }
}
