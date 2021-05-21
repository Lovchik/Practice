package by.vit.ban.dao;

import by.vit.ban.model.Employee;

import java.util.List;

public interface EmployeeDao {

    void save(Employee employee);

    void delete(Long id);

    List<Employee> getAll();

    void update(Employee employee);
}
