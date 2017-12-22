package butterfly.blue.bankapp.sqlite.model;

/**
 * Created by Valryo on 13/10/2017.
 */

public class BankAccount {
    private long id = -1;
    private long CreatedAt;
    private long ModifiedAt;
    private String Name;
    private String BankName;
    private float Amount;

    public BankAccount(){

    }

    public BankAccount(String name, String bankName) {
        this.Name = name;
        this.BankName = bankName;
        this.Amount = 0f;
    }

    // -===- Getters -===-
    public long getId(){
        return this.id;
    }

    public long getCreatedAt(){
        return this.CreatedAt;
    }

    public long getModifiedAt(){
        return this.ModifiedAt;
    }

    public String getName(){
        return this.Name;
    }

    public String getBankName(){
        return this.BankName;
    }

    public float getAmount() {
        return Amount;
    }

    // -===- Setters -===-
    public void setId(long id){
        this.id = id;
    }

    public void setCreatedAt(long creation){
        this.CreatedAt = creation;
    }

    public void setModifiedAt(long modifiedAt){
        this.ModifiedAt = modifiedAt;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setBankName(String bankName) {
        this.BankName = bankName;
    }

    public String toString(){
        return this.BankName + " - " + this.Name + " : " + this.Amount;
    }

    public void setAmount(float amount) {
        Amount = amount;
    }
}
