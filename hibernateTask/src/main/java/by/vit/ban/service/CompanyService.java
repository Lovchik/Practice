package by.vit.ban.service;

import by.vit.ban.dao.CompanyDao;
import by.vit.ban.dao.EmployeeDao;
import by.vit.ban.dao.implementation.CompanyDaoImpl;
import by.vit.ban.dao.implementation.EmployeeDaoImpl;
import by.vit.ban.model.Company;
import by.vit.ban.model.Employee;
import by.vit.ban.util.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CompanyService {
    private final CompanyDao companyDao = new CompanyDaoImpl();
    private final EmployeeDao employeeDao = new EmployeeDaoImpl();

    public void createCompanyWithEmployees(Company company, List<Employee> employees) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            companyDao.save(company);
            employees.forEach(employeeDao::save);
            transaction.commit();
        } catch (HibernateException exception) {
            if (transaction != null) {
                transaction.rollback();
            }
        }finally {
            session.close();
        }
    }
}
