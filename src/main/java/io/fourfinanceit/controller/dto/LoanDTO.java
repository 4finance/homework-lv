package io.fourfinanceit.controller.dto;

import java.io.Serializable;
import java.util.Date;

public class LoanDTO implements Serializable {

    private Long id;

    private Float loanSum;

    private Float interestRate;

    private Date created;

    private Integer duration;

    private LoanExtensionDTO loanExtension;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LoanExtensionDTO getLoanExtension() {
        return loanExtension;
    }

    public void setLoanExtension(LoanExtensionDTO loanExtension) {
        this.loanExtension = loanExtension;
    }
}
