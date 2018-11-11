package io.fourfinanceit.controller.dto;

import java.io.Serializable;
import java.util.Date;

public class LoanRequestDTO implements Serializable {

    private Long id;

    private Long clientId;

    private String ipAddress;

    private Date created;

    private Float requestedSum;

    private Integer duration;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Float getRequestedSum() {
        return requestedSum;
    }

    public void setRequestedSum(Float requestedSum) {
        this.requestedSum = requestedSum;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
