package by.vit.ban.dao;

import by.vit.ban.database.Datasource;
import by.vit.ban.model.Person;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PersonDaoImpl implements PersonDao {

    private static final String INSERT_INTO_CLIENT = "INSERT INTO CLIENT (FIRSTNAME,LASTNAME,PHONENUMBER,PASSWORD) VALUES (?,?,?,?)";

    private static final String SELECT_ALL_FROM_CLIENT = "SELECT * FROM CLIENT";

    private static final String UPDATE_EXIST_CLIENT = "UPDATE CLIENT SET FIRSTNAME = ?, LASTNAME = ?, PHONENUMBER = ?,PASSWORD = ? WHERE ID = ?";

    private static final String FIND_BY_ID = "SELECT * FROM CLIENT WHERE id =?";

    private static final String DELETE_CLIENT_BY_ID = "DELETE FROM CLIENT where ID = ?";
    private static final Logger logger = (Logger) LogManager.getLogger();
    private final Datasource datasource = Datasource.getInstance();

    /**
     * Method to insert a person in the database
     *
     * @param person the person that will be inserted
     * @return person the inserted
     */
    public Person insert(Person person) {
        PreparedStatement statement;
        try {
            statement = datasource.getStatement(INSERT_INTO_CLIENT);
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getSecondName());
            statement.setString(3, person.getPhoneNumber());
            statement.setString(4, person.getPassword());
            statement.executeUpdate();
        } catch (SQLException exception) {
            logger.error("Wrong input data", exception);
        }
        return person;
    }

    /**
     * Method to retrieve all users from the database
     *
     * @return users all users in the database
     */
    @SneakyThrows
    public List<Person> getAll() {
        try (PreparedStatement statement = datasource.getStatement(SELECT_ALL_FROM_CLIENT);
             ResultSet resultSet = statement.executeQuery()) {
            List<Person> users = new ArrayList<>();
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getLong("ID"));
                person.setFirstName(resultSet.getString("FIRSTNAME"));
                person.setSecondName(resultSet.getString("LASTNAME"));
                person.setPhoneNumber(resultSet.getString("PHONENUMBER"));
                person.setPassword(resultSet.getString("PASSWORD"));
                users.add(person);
            }
            return users;
        }
    }

    /**
     * Method to find a person in the database by id
     *
     * @param id id
     * @return person the searched
     */
    @SneakyThrows
    @Override
    public Person findById(int id) {
        ResultSet resultSet = null;
        Person person = null;
        try (PreparedStatement statement = datasource.getStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                person = new Person();
                person.setId(resultSet.getLong("ID"));
                person.setFirstName(resultSet.getString("FIRSTNAME"));
                person.setSecondName(resultSet.getString("LASTNAME"));
                person.setPhoneNumber(resultSet.getString("PHONENUMBER"));
                person.setPassword(resultSet.getString("PASSWORD"));
            } else {
                return null;
            }
        } catch (SQLException exception) {
            logger.error("Person with this id does not exist", exception);
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return person;
    }

    /**
     * Method to delete a person in the database by id
     *
     * @param id id
     */

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = datasource.getStatement(DELETE_CLIENT_BY_ID)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            logger.error("Person with this id does not exist", exception);
        }
    }

    /**
     * Method to update a person in the database
     *
     * @param person person
     * @return person
     */
    @Override
    public Person update(Person person) {
        try (PreparedStatement statement = datasource.getStatement(UPDATE_EXIST_CLIENT)) {
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getSecondName());
            statement.setString(3, person.getPhoneNumber());
            statement.setString(4, person.getPassword());
            statement.setLong(5, person.getId());
            statement.executeUpdate();
            return person;
        } catch (SQLException exception) {
            logger.error("Wrong input data or current person does not exist", exception);
            return null;
        }
    }
}
