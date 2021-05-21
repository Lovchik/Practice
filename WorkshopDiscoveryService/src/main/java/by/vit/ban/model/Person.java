package by.vit.ban.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "coordinates")
    private String coordinates;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private List<Request> requests;

    @Column(name = "firstName",nullable = false)
    private String firstName;

    @Column(name = "lastName",nullable = false)
    private String lastName;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "phoneNumber",nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "role",nullable = false)
    private Role role;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "master")
    private List<Contract> contract;

    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "idworkPlace")
    private Workshop workPlace;

    @Column(name = "isBusy")
    private Boolean status; 

    public Person(boolean status) {
        this.status = status;
    }

    public Person() {
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public List<Contract> getContract() {
        return contract;
    }

    public void setContract(List<Contract> contract) {
        this.contract = contract;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Workshop getWorkPlace() {
        return workPlace;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public void setWorkPlace(Workshop workPlace) {
        this.workPlace = workPlace;
    }

    public Contract createContractFromLastConditionalRequest(Request request) {
        if (request != null && workPlace.isPossibleToCompleteRequest(request)) {
            workPlace.getRequests().remove(request);
            Person master = workPlace.getFreeMaster();
            master.setStatus(false);
            Contract acceptedContract = new Contract(master, Status.ACCEPTED, request);
            workPlace.getContracts().add(acceptedContract);
            return acceptedContract;
        }

        return null;
    }

    public void completedRepair(Contract contract) {
        contract.setStatus(Status.COMPLETED);
        setStatus(true);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", coordinates='" + coordinates + '\'' +
                ", requests=" + requests +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + role +
                ", contract=" + contract +
                ", workPlace=" + workPlace +
                ", status=" + status +
                '}';
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

