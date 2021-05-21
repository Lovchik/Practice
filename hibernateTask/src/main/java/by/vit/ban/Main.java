package by.vit.ban;

import by.vit.ban.dao.CompanyDao;
import by.vit.ban.dao.EmployeeDao;
import by.vit.ban.dao.implementation.CompanyDaoImpl;
import by.vit.ban.dao.implementation.EmployeeDaoImpl;
import by.vit.ban.model.Company;
import by.vit.ban.model.Employee;
import by.vit.ban.service.CompanyService;


public class Main {
    public static void main(String[] args) {
        EmployeeDao  employeeDao = new EmployeeDaoImpl();
        CompanyDao companyDao = new CompanyDaoImpl();
        CompanyService companyService = new CompanyService();
        Company company = new Company("NewCompany", 13214, "Shapavalova12");
        companyDao.save(company);
        Employee employee = new Employee("Den", "Bor", "80295165165", "Java Dev", company);
        employeeDao.save(employee);
        company.setCompanyName("NEWCOMPANY");
        employee.setName("DENIS");
    }
}
