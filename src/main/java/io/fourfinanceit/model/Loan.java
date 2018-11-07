package io.fourfinanceit.model;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.text.DecimalFormat;

@Entity
@Table(name = "LOANS")
public class Loan {


    @Id
    @Column(name = "LOAN_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long loanId;
    @Column(name = "AMOUNT", nullable = false)
    private double amount;
    @Column(name = "ISSUED_AT")
    private DateTime issuedAt;
    @Column(name = "PAYMENT_EXPECTATION_DATE")
    private DateTime paymentExpectationDate;
    @Column(name = "PAYMENT_EXPECTATION_DATE_EXTENDED", nullable = true)
    private DateTime paymentExpectationDateExtended;
    @Column(name = "PAID_AT", nullable = true)
    private DateTime paidAt;


    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        DecimalFormat df = new DecimalFormat("#.00");
        this.amount = Double.valueOf(df.format(amount));
    }

    public DateTime getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(DateTime issuedAt) {
        this.issuedAt = issuedAt;
    }

    public DateTime getPaymentExpectationDate() {
        return paymentExpectationDate;
    }

    public void setPaymentExpectationDate(DateTime paymentExpectationDate) {
        this.paymentExpectationDate = paymentExpectationDate;
    }

    public DateTime getPaymentExpectationDateExtended() {
        return paymentExpectationDateExtended;
    }

    public void setPaymentExpectationDateExtended(DateTime paymentExpectationDateExtended) {
        this.paymentExpectationDateExtended = paymentExpectationDateExtended;
    }

    public DateTime getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(DateTime paidAt) {
        this.paidAt = paidAt;
    }
}
