package butterfly.blue.bankapp.sqlite.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterfly.blue.bankapp.sqlite.model.BankAccount;
import butterfly.blue.bankapp.sqlite.model.Transaction;

/**
 * Created by Star Dust on 13/10/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DATABASEHELPER";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "TransactionDB";

    // Table names
    private static final String TABLE_BANK_ACCOUNT = "Bank_Account";
    private static final String TABLE_TRANSACTION = "MyTransaction";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "CreatedAt";
    private static final String KEY_MODIFIED_AT = "ModifiedAt";

    // Bank Account table - Column names
    private static final String KEY_NAME = "Name";
    private static final String KEY_BANK_NAME = "BankName";
    private static final String KEY_BANK_AMOUNT = "Amount";

    // Transaction table - Column names
    private static final String KEY_BANK_ACCOUNT_ID = "BankAccountId";
    private static final String KEY_AMOUNT = "Amount";
    private static final String KEY_TRANSACTION_DATE = "TransactionDate";
    private static final String KEY_IS_INCOME = "IsIncome";

    // Table create segment
    private static final String CREATE_TABLE_BANK_ACCOUNT =
            "CREATE TABLE " + TABLE_BANK_ACCOUNT + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_CREATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP, " + KEY_MODIFIED_AT
            + " DATETIME DEFAULT CURRENT_TIMESTAMP, " + KEY_NAME + " TEXT NOT NULL, "
            + KEY_BANK_NAME + " TEXT NOT NULL, " + KEY_BANK_AMOUNT + " FLOAT)";

    private static final String CREATE_TABLE_TRANSACTION =
            "CREATE TABLE " + TABLE_TRANSACTION + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_CREATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP, " + KEY_MODIFIED_AT
            + " DATETIME DEFAULT CURRENT_TIMESTAMP, " + KEY_AMOUNT + " INTEGER, "
            + KEY_TRANSACTION_DATE + " DATETIME, " + KEY_IS_INCOME + " BOOLEAN, "
            + KEY_BANK_ACCOUNT_ID + " INTEGER, "
            + "FOREIGN KEY (" + KEY_BANK_ACCOUNT_ID + ") REFERENCES " + TABLE_BANK_ACCOUNT + "("
            + KEY_ID + "))";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BANK_ACCOUNT);
        db.execSQL(CREATE_TABLE_TRANSACTION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BANK_ACCOUNT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTION);

        onCreate(db);
    }

    /*
    * Creating a new bank account
    */
    public long createBankAccount(BankAccount bankAccount) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, bankAccount.getName());
        values.put(KEY_BANK_NAME, bankAccount.getBankName());
        values.put(KEY_BANK_AMOUNT, bankAccount.getAmount());

        long id = db.insert(TABLE_BANK_ACCOUNT, null, values);
        bankAccount.setId(id);

        return id;
    }

    /*
    * Get a single bank account with id
     */
    public BankAccount getBankAccount(long id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_BANK_ACCOUNT + " WHERE " + KEY_ID + " = " + id;
        Log.d(TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if(c == null || !c.moveToFirst())
            return null;

        BankAccount ba = new BankAccount();
        ba.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        ba.setName(c.getString(c.getColumnIndex(KEY_NAME)));
        ba.setBankName(c.getString(c.getColumnIndex(KEY_BANK_NAME)));
        ba.setAmount(c.getFloat(c.getColumnIndex(KEY_BANK_AMOUNT)));

        return ba;
    }

    /*
    * Get all bank account
     */
    public List<BankAccount> getAllBankAccount() {
        List<BankAccount> accounts = new ArrayList<BankAccount>();
        String selectQuery = "SELECT * FROM " + TABLE_BANK_ACCOUNT;
        Log.d(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if(c.moveToFirst()) {
            do {
                BankAccount ba = new BankAccount();
                ba.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                ba.setName(c.getString(c.getColumnIndex(KEY_NAME)));
                ba.setBankName(c.getString(c.getColumnIndex(KEY_BANK_NAME)));
                ba.setAmount(c.getFloat(c.getColumnIndex(KEY_BANK_AMOUNT)));

                accounts.add(ba);
            } while(c.moveToNext());
        }
        return accounts;
    }

    /*
    * Updating a bank account
     */
    public int updateBankAccount(BankAccount bankAccount){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, bankAccount.getName());
        values.put(KEY_BANK_NAME, bankAccount.getBankName());
        values.put(KEY_BANK_AMOUNT, bankAccount.getAmount());
        values.put(KEY_MODIFIED_AT, System.currentTimeMillis() / 1000L);

        return db.update(TABLE_BANK_ACCOUNT, values, KEY_ID + " = ?",  new String[] {String.valueOf(bankAccount.getId())});
    }

    /*
    * Deleting a bank account
     */
    public void deleteBankAccount(long bankAccountId){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BANK_ACCOUNT, KEY_ID + " = ?", new String[] {String.valueOf(bankAccountId)});
    }

    /*
    * Creating a transaction
     */
    public long createTransaction(Transaction transaction){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_AMOUNT, transaction.getAmount());
        values.put(KEY_TRANSACTION_DATE, transaction.getTransactionDate());
        values.put(KEY_IS_INCOME, transaction.isIncome());
        values.put(KEY_BANK_ACCOUNT_ID, transaction.getAccountId());

        long id = db.insert(TABLE_TRANSACTION, null, values);
        transaction.setId(id);

        return id;
    }

    /*
    * Get a single transaction with id
     */
    public Transaction getTransaction(long id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_TRANSACTION + " WHERE " + KEY_ID + " = " + id;
        Log.d(TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if(c != null)
            c.moveToFirst();

        Transaction transaction = new Transaction();
        transaction.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        transaction.setAmount(c.getFloat(c.getColumnIndex(KEY_AMOUNT)));
        transaction.setTransactionDate(c.getInt(c.getColumnIndex(KEY_TRANSACTION_DATE)));
        transaction.setIncome(c.getInt(c.getColumnIndex(KEY_IS_INCOME)) > 0);
        transaction.setAccountId(c.getInt(c.getColumnIndex(KEY_BANK_ACCOUNT_ID)));

        return transaction;
    }

    /*
    * Get all transactions
     */
    public List<Transaction> getAllTransaction(){
        List<Transaction> transactions = new ArrayList<Transaction>();
        String selectQuery = "SELECT * FROM " + TABLE_TRANSACTION;
        Log.d(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if(c.moveToFirst()) {
            do {
                Transaction transaction = new Transaction();
                transaction.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                transaction.setAmount(c.getFloat(c.getColumnIndex(KEY_AMOUNT)));
                transaction.setTransactionDate(c.getInt(c.getColumnIndex(KEY_TRANSACTION_DATE)));
                transaction.setIncome(c.getInt(c.getColumnIndex(KEY_IS_INCOME)) > 0);
                transaction.setAccountId(c.getInt(c.getColumnIndex(KEY_BANK_ACCOUNT_ID)));

                transactions.add(transaction);
            } while(c.moveToNext());
        }
        return transactions;
    }

    /*
    * Updating a bank account
     */
    public int updateTransaction(Transaction transaction){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_AMOUNT, transaction.getAmount());
        values.put(KEY_TRANSACTION_DATE, transaction.getTransactionDate());
        values.put(KEY_IS_INCOME, transaction.isIncome());
        values.put(KEY_BANK_ACCOUNT_ID, transaction.getAccountId());
        values.put(KEY_MODIFIED_AT, System.currentTimeMillis() / 1000L);

        return db.update(TABLE_TRANSACTION, values, KEY_ID + " = " + transaction.getId(), null);
    }

    /*
    * Deleting a bank account
     */
    public void deleteTransaction(long transactionId){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TRANSACTION, KEY_ID + " = ?", new String[] {String.valueOf(transactionId)});
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
