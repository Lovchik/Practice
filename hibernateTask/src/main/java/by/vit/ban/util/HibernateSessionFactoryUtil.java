package by.vit.ban.util;


import by.vit.ban.model.Company;
import by.vit.ban.model.Employee;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;
    private static Logger logger = Logger.getLogger("HibernateSessionFactoryUtil");

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Company.class);
                configuration.addAnnotatedClass(Employee.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (HibernateException e) {
                logger.log(Level.WARNING,"Hibernate Exception");
            }
        }
        return sessionFactory;
    }
}
