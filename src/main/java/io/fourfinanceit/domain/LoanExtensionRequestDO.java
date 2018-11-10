package io.fourfinanceit.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "LOAN_EXTENSION")
public class LoanExtensionRequestDO {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private ClientDO client;

    @ManyToOne
    @JoinColumn(name = "LOAN_ID")
    private LoanDO loan;

    @Column(name = "IP_ADDRESS")
    private String ipAddress;

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

    public LoanDO getLoan() {
        return loan;
    }

    public void setLoan(LoanDO loan) {
        this.loan = loan;
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
