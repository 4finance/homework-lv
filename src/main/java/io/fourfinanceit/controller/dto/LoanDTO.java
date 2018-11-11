package io.fourfinanceit.controller.dto;

import io.fourfinanceit.domain.LoanExtensionDO;

import java.io.Serializable;
import java.util.Date;

public class LoanDTO implements Serializable {

    private Long id;

    private Float loanSum;

    private Float interestRate;

    private Date created;

    private Integer duration;

    private LoanExtensionDO loanExtension;

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

    public LoanExtensionDO getLoanExtension() {
        return loanExtension;
    }

    public void setLoanExtension(LoanExtensionDO loanExtension) {
        this.loanExtension = loanExtension;
    }
}
