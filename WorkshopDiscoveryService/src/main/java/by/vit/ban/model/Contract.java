package by.vit.ban.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="contract")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="idMaster")
    private Person master;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private Status status;

    @JsonIgnore
    @OneToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="idRequest")
    private Request request;

    @ManyToOne ( cascade=CascadeType.ALL)
    @JoinColumn (name="idWorkshop")
    private Workshop workshop;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Contract(Person master, Status status, Request request) {
        this.master = master;
        this.status = status;
        this.request = request;
    }

    public Contract() {
    }

    public Contract(Person master, Status status, Request request, Workshop workshop) {
        this.master = master;
        this.status = status;
        this.request = request;
        this.workshop = workshop;
    }

    public Contract(Person master, Status status) {
        this.master = master;
        this.status = status;
    }

    public Person getMaster() {
        return master;
    }

    public void setMaster(Person master) {
        this.master = master;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
