package by.vit.ban.dao.implementation;

import by.vit.ban.dao.CompanyDao;
import by.vit.ban.model.Company;
import by.vit.ban.model.Employee;
import by.vit.ban.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CompanyDaoImpl implements CompanyDao {


    @Override
    public void save(Company company) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(company);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Company company = HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Company.class,id);
        session.delete(company);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Company> getAll() {
        List<Company> companies;
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            companies=session.createQuery("SELECT a FROM Company a", Company.class).getResultList();
            return companies;
        }
    }

    @Override
    public void update(Company company) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(company);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Employee> getEmployeesByCompanyNameWithSpecialty(String name, String speciality) {
        Transaction transaction;
        List<Employee> employeesBySpec;
        Query query;
        try(Session session =HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            transaction=session.beginTransaction();
            query = session.createQuery("from Employee where specialty=:speciality and company.companyName=:name");
        }
        query.setParameter(1,speciality);
        query.setParameter(2,name);
        employeesBySpec=query.list();
        transaction.commit();
        return employeesBySpec;
    }
}
