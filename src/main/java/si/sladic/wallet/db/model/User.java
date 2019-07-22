package si.sladic.wallet.db.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private Balance balance;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;

    public Long getId() {
        return this.id;
    }

    public Balance getBalance() {
        return this.balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
