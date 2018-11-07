package io.fourfinanceit.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "USERS")
@NamedQueries({@NamedQuery(name = "io.fourfinanceit.model.User.findAll", query = "select e from User e")	})
public class User {

    @Id
    @Column(name = "USER_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    @Column(name = "NAME", length = 100, nullable = false, unique = true)
    private String name;
    @Column(name = "MAX_AMOUNT", nullable = false)
    private long maxAmount;
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Loan> loans;

    public User(){
    }

    public User(String name, long maxAmount){
        this.name = name;
        this.maxAmount = maxAmount;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public long getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(long maxAmount) {
        this.maxAmount = maxAmount;
    }

}
