package io.fourfinanceit.model;

import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "LOAN_REQUESTS")
public class LoanRequest {

    @Id
    @Column(name = "LOAN_REQUEST_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long loanRequestId;
    @Column(name = "REQUESTED_AT")
    private DateTime requestedAt;
    @Column(name = "AMOUNT", nullable = false)
    private int amount;
    @Column(name = "REQUESTED_FROM_IP", nullable = false)
    private String requestedFromIp;

    public LoanRequest(){

    }

    public LoanRequest(int amount, String requestedFromIp) {
        this.amount = amount;
        this.requestedFromIp = requestedFromIp;
        this.requestedAt = DateTime.now();
    }

    public String getRequestedFromIp() {
        return requestedFromIp;
    }

    public void setRequestedFromIp(String requestedFromIp) {
        this.requestedFromIp = requestedFromIp;
    }

    public DateTime getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(DateTime requestedAt) {
        this.requestedAt = requestedAt;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getLoanRequestId() {
        return loanRequestId;
    }

    public void setLoanRequestId(long loanRequestId) {
        this.loanRequestId = loanRequestId;
    }
}
