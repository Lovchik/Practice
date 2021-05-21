package by.vit.ban.dao.implementation;

import by.vit.ban.dao.EmployeeDao;
import by.vit.ban.model.Company;
import by.vit.ban.model.Employee;
import by.vit.ban.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public void save(Employee employee) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(employee);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Employee employee = HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Employee.class,id);
        session.delete(employee);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> employees;
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            employees=session.createQuery("SELECT a FROM Employee a", Employee.class).getResultList();
            return employees;
        }
    }

    @Override
    public void update(Employee employee) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(employee);
        transaction.commit();
        session.close();
    }
}
