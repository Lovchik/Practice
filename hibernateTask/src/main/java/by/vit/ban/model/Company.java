package by.vit.ban.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "registration_number")
    private int registrationNumber;

    @Column(name = "address")
    private String address;


    public Company(String companyName, int registrationNumber, String address) {
        this.companyName = companyName;
        this.registrationNumber = registrationNumber;
        this.address = address;
    }
}