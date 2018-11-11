package io.fourfinanceit.controller.dto;

import java.io.Serializable;
import java.util.Date;

public class LoanExtensionDTO implements Serializable {

    private Long id;

    private Integer duration;

    private Float interestRate;

    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
