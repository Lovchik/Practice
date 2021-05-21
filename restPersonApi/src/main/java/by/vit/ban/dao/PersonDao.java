package by.vit.ban.dao;

import by.vit.ban.model.Person;

import java.util.List;

public interface PersonDao {
    Person insert(Person person);

    List<Person> getAll();

    Person findById(int id);

    void delete(int id);

    Person update(Person person);
}