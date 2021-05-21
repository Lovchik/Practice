package by.vit.ban.dao;

import by.vit.ban.model.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PersonDaoImplTest {
    @Mock
    private final PersonDao personDao = new PersonDaoImpl();

    @Test
    public void findByIdTest() {
        Person person = new Person(1, "Vitaly", "Lovchik", "general", "+375297068350");
        Mockito.when(personDao.findById(1)).thenReturn(person);
        Assert.assertEquals(person, personDao.findById(1));
    }

    @Test
    public void insetTest() {
        Person person = new Person(3, "Vitaly", "Lovchik", "general", "+375297068350");
        Mockito.when(personDao.insert(person)).thenReturn(person);
        Assert.assertEquals(person, personDao.insert(person));
    }

    @Test
    public void getAllPersonsFromDataBaseTest() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1, "Vitaly", "Lovchik", "general", "+375297068350"));
        personList.add(new Person(2, "Sasha", "Casha", "gevvr", "+375297537543"));
        Mockito.when(personDao.getAll()).thenReturn(personList);
        Assert.assertEquals(personList, personDao.getAll());
    }

    @Test
    public void updateTest() {
        Person person = new Person(1, "1", "Lovchik", "general", "+375297068350");
        Mockito.when(personDao.update(person)).thenReturn(person);
        Assert.assertEquals(person, personDao.update(person));
    }

    @Test
    public void deleteByIdTest() {
        personDao.delete(1);
        Assert.assertNull(personDao.findById(1));
    }
}
