package butterfly.blue.bankapp.test;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import butterfly.blue.bankapp.sqlite.helper.DatabaseHelper;
import butterfly.blue.bankapp.sqlite.model.BankAccount;
import butterfly.blue.bankapp.sqlite.model.Transaction;

import static org.junit.Assert.*;

/**
 * Created by Star Dust on 16/10/2017.
 */
public class DatabaseHelperTest {
    Context appContext = InstrumentationRegistry.getTargetContext();
    DatabaseHelper db;


    @Before
    public void setUp() throws Exception {
            db = new DatabaseHelper(appContext);
            db.onUpgrade(db.getWritableDatabase(), 1, 2);
    }

    @After
    public void tearDown() throws Exception {
        db.closeDB();
    }

    @Test
    public void createBankAccount() throws Exception {
        BankAccount ba1 = new BankAccount("Cheque", "Desjardins");
        BankAccount ba2 = new BankAccount("Livret jeune", "Banque Populaire");

        db.createBankAccount(ba1);
        db.createBankAccount(ba2);

        assertEquals(2, db.getAllBankAccount().size());
    }

    @Test
    public void getBankAccount() throws Exception {
        assertNull(db.getBankAccount(1));

        BankAccount ba1 = new BankAccount("Livret jeune", "Banque Populaire");
        long ba1_id = db.createBankAccount(ba1);

        ba1 = db.getBankAccount(ba1_id);
        assertNotNull(ba1);
        assertEquals("Livret jeune", ba1.getName());
        assertEquals("Banque Populaire", ba1.getBankName());
        assertEquals(ba1_id, ba1.getId());
    }

    @Test
    public void getAllBankAccount() throws Exception {
        assertEquals(0, db.getAllBankAccount().size());

        BankAccount ba1 = new BankAccount("Cheque", "Desjardins");
        BankAccount ba2 = new BankAccount("Livret jeune", "Banque Populaire");

        db.createBankAccount(ba1);
        db.createBankAccount(ba2);

        assertEquals(2, db.getAllBankAccount().size());
    }

    @Test
    public void updateBankAccount() throws Exception {
        BankAccount ba = new BankAccount("Livret jeune", "Banque Populaire");
        long ba_id = db.createBankAccount(ba);

        ba.setName("Compte Chèques");
        ba.setBankName("Desjardins Chicoutimi");
        db.updateBankAccount(ba);

        ba = db.getBankAccount(ba_id);
        assertEquals("Compte Chèques", ba.getName());
        assertEquals("Desjardins Chicoutimi", ba.getBankName());
        assertEquals(ba_id, ba.getId());
    }

    @Test
    public void deleteBankAccount() throws Exception {
        BankAccount ba1 = new BankAccount("Cheque", "Desjardins");
        BankAccount ba2 = new BankAccount("Livret jeune", "Banque Populaire");

        db.createBankAccount(ba1);
        db.createBankAccount(ba2);

        db.deleteBankAccount(ba2.getId());

        assertEquals(1, db.getAllBankAccount().size());
    }

    @Test
    public void createTransaction() throws Exception {
        assertEquals(0, db.getAllTransaction().size());

        Transaction t1 = new Transaction(50, System.currentTimeMillis() /1000L, true, 1);
        Transaction t2 = new Transaction(150, System.currentTimeMillis() /1000L, false, 2);

        db.createTransaction(t1);
        db.createTransaction(t2);

        assertEquals(2, db.getAllTransaction().size());
    }

    @Test
    public void getTransaction() throws Exception {
        long date = System.currentTimeMillis() /1000L;
        Transaction t1 = new Transaction(50, date, true, 1);
        long t_id = db.createTransaction(t1);

        t1 = db.getTransaction(t_id);
        assertNotNull(t1);
        assertEquals(50, t1.getAmount());
        assertEquals(date, t1.getTransactionDate());
        assertEquals(1, t1.getAccountId());
        assertTrue(t1.isIncome());
    }

    @Test
    public void getAllTransaction() throws Exception {
        assertEquals(0, db.getAllTransaction().size());

        Transaction t1 = new Transaction(50, System.currentTimeMillis() /1000L, true, 1);
        Transaction t2 = new Transaction(150, System.currentTimeMillis() /1000L, false, 2);

        db.createTransaction(t1);
        db.createTransaction(t2);

        assertEquals(2, db.getAllTransaction().size());
    }

    @Test
    public void updateTransaction() throws Exception {
        long date = System.currentTimeMillis() /1000L;
        Transaction t1 = new Transaction(50, date, true, 1);
        long t_id = db.createTransaction(t1);

        t1.setAmount(150);
        t1.setIncome(false);
        t1.setAccountId(2);
        t1.setTransactionDate(System.currentTimeMillis() /1000L + 1);

        db.updateTransaction(t1);

        t1 = db.getTransaction(t_id);
        assertNotNull(t1);
        assertEquals(150, t1.getAmount());
        assertEquals(date + 1, t1.getTransactionDate());
        assertEquals(2, t1.getAccountId());
        assertFalse(t1.isIncome());
    }

    @Test
    public void deleteTransaction() throws Exception {
        Transaction t1 = new Transaction(50, System.currentTimeMillis() /1000L, true, 1);
        Transaction t2 = new Transaction(150, System.currentTimeMillis() /1000L, false, 2);

        db.createTransaction(t1);
        db.createTransaction(t2);

        db.deleteTransaction(t2.getId());

        assertEquals(1, db.getAllTransaction().size());

        t1 = db.getTransaction(t1.getId());
        assertNotNull(t1);
        assertEquals(50, t1.getAmount());
    }

}