package io.fourfinanceit.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "LOAN_EXTENSION")
public class LoanExtensionDO {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "LOAN_ID")
    private LoanDO loan;

    @Column(name = "DURATION")
    private Integer duration;

    @Column(name = "INTEREST_RATE")
    private Float interestRate;

    @Column(name = "CREATED")
    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LoanDO getLoan() {
        return loan;
    }

    public void setLoan(LoanDO loan) {
        this.loan = loan;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Float interestRate) {
        this.interestRate = interestRate;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
