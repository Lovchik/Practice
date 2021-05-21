package by.vit.ban.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "specialty")
    private String specialty;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_company", referencedColumnName = "company_id")
    private Company company;

    public Employee(String name, String lastname, String phoneNumber, String specialty, Company company) {
        this.name = name;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.specialty = specialty;
        this.company = company;
    }
}
