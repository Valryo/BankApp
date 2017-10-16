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
/*Transaction t1 = new Transaction(50, System.currentTimeMillis() /1000L, true, ba1_id);
        Transaction t2 = new Transaction(150, System.currentTimeMillis() /1000L, false, ba1_id);
        Transaction t3 = new Transaction(250, System.currentTimeMillis() /1000L, true, ba2_id);
        Transaction t4 = new Transaction(50, System.currentTimeMillis() /1000L, false, ba2_id);

        db.createTransaction(t1);
        db.createTransaction(t2);
        db.createTransaction(t3);
        db.createTransaction(t4);

        List<Transaction> transactions = db.getAllTransaction();
       // Log.d(TAG, "Transactions : " + transactions.size());

        for(int i = 0; i < transactions.size(); i++){
            //Log.d(TAG, transactions.get(i).toString());
        }

        t1.setAmount(755);
        t1.setTransactionDate(System.currentTimeMillis() /1000L);
        db.updateTransaction(t1);
        //Log.d(TAG, db.getTransaction(t1.getId()).toString());

        db.deleteTransaction(t2.getId());

        //Log.d(TAG, "Transaction count: " + db.getAllTransaction().size());

        db.closeDB();*/
    }

    @Test
    public void getTransaction() throws Exception {

    }

    @Test
    public void getAllTransaction() throws Exception {

    }

    @Test
    public void updateTransaction() throws Exception {

    }

    @Test
    public void deleteTransaction() throws Exception {

    }

}