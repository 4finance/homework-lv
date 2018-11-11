package io.fourfinanceit.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "LOAN")
public class LoanDO {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private ClientDO client;

    @OneToOne
    @JoinColumn(name = "EXTENSION_ID")
    private LoanExtensionDO loanExtension;

    @Column(name = "LOAN_SUM")
    private Float loanSum;

    @Column(name = "INTEREST_RATE")
    private Float interestRate;

    @Column(name = "MONTHLY_SUM")
    private Float monthlySum;

    @Column(name = "CREATED")
    private Date created;

    @Column(name = "DURATION")
    private Integer duration;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientDO getClient() {
        return client;
    }

    public void setClient(ClientDO client) {
        this.client = client;
    }

    public LoanExtensionDO getLoanExtension() {
        return loanExtension;
    }

    public void setLoanExtension(LoanExtensionDO loanExtension) {
        this.loanExtension = loanExtension;
    }

    public Float getLoanSum() {
        return loanSum;
    }

    public void setLoanSum(Float loanSum) {
        this.loanSum = loanSum;
    }

    public Float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Float interestRate) {
        this.interestRate = interestRate;
    }

    public Float getMonthlySum() {
        return monthlySum;
    }

    public void setMonthlySum(Float monthlySum) {
        this.monthlySum = monthlySum;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
