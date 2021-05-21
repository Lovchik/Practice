package by.vit.ban.repository;

import by.vit.ban.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    Optional <Person> getPersonByPasswordAndPhoneNumber(String password, String phoneNumber);
    List<Person> findByWorkPlaceId(Long id);
}
