package by.vit.ban.service;

import by.vit.ban.model.Person;
import by.vit.ban.model.Request;
import by.vit.ban.model.Status;
import by.vit.ban.repository.PersonRepository;
import by.vit.ban.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {
    private  final RequestRepository requestRepository;
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository,RequestRepository requestRepository) {
        this.personRepository = personRepository;
        this.requestRepository = requestRepository;
    }

    public void update(Person person) {
        person.setStatus(true);
        person.getContract().stream().filter(contract -> contract.getStatus() == Status.ACCEPTED).findFirst().get().setStatus(Status.COMPLETED);
        personRepository.save(person);
    }

    public  void deleteRequestById(Long id){
        Optional<Request> request = requestRepository.findById(id);
        if(request.isPresent()){
            Optional<Person> person = personRepository.findById(request.get().getClient().getId());
            if(person.isPresent()){
            person.get().getRequests().remove(request.get());
            personRepository.save(person.get());
            requestRepository.delete(request.get());}
        }

    }
}
