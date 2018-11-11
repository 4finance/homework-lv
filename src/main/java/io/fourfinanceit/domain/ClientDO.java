package io.fourfinanceit.domain;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CLIENT")
public class ClientDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "PERSONALCODE")
    private String personalCode;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATED")
    private Date created;

    public ClientDO() {
    }

    public ClientDO(Long clientId) {
        this.id = clientId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
