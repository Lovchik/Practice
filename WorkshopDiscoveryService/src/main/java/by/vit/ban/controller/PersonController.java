package by.vit.ban.controller;

import by.vit.ban.model.Person;
import by.vit.ban.repository.PersonRepository;
import by.vit.ban.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/person")
public class PersonController {
    private final PersonRepository personRepository;
    private final PersonService personService;

    @Autowired
    public PersonController(PersonRepository personRepository, PersonService personService) {
        this.personRepository = personRepository;
        this.personService = personService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<Person> getPersons() {
        return (List<Person>) personRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Person> getPersonByPassAndPhoneNumber(@RequestBody Person person) {
        Optional<Person> personOptional = personRepository.getPersonByPasswordAndPhoneNumber(person.getPassword(), person.getPhoneNumber());
        return personOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{personId}")
    public ResponseEntity<Person> getPersonById(@PathVariable(name = "personId") Long id) {
        Optional<Person> personOptional = personRepository.findById(id);
        return personOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/{personId}")
    public ResponseEntity<Void> completedRepair(@PathVariable(name = "personId") Long personId) {

        Optional<Person> personOptional = personRepository.findById(personId);
        if (personOptional.isPresent()) {
            personService.update(personOptional.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(("/request/{requestId}"))
    public ResponseEntity<Void> deleteUsersRequest(@PathVariable(name = "requestId") Long requestId) {
        personService.deleteRequestById(requestId);
        return ResponseEntity.ok().build();
    }
}
