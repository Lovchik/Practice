package by.vit.ban.dao;

import by.vit.ban.model.Company;
import by.vit.ban.model.Employee;

import java.util.List;

public interface CompanyDao {
    void save(Company company);

    void delete(Long id);

    List<Company> getAll();

    void update(Company company);

    List<Employee> getEmployeesByCompanyNameWithSpecialty(String name, String speciality);

}
