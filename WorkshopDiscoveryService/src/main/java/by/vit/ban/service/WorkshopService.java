package by.vit.ban.service;

import by.vit.ban.model.Person;
import by.vit.ban.model.Request;
import by.vit.ban.model.Workshop;
import by.vit.ban.model.WorkshopDiscovery;
import by.vit.ban.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkshopService {
    private final WorkshopDiscovery workshopDiscovery;
    private final PersonRepository personRepository;

    @Autowired
    public WorkshopService(WorkshopDiscovery workshopDiscovery, PersonRepository personRepository) {
        this.workshopDiscovery = workshopDiscovery;
        this.personRepository = personRepository;
    }

    public Workshop getWorkshopNearby(Request request) {
        return workshopDiscovery.findWorkshopsNearby(request.getCarServices(), request.getClient().getCoordinates(), false);
    }

    public Workshop getCheaperWorkshop(Request request){
        return workshopDiscovery.findWorkshopsNearby(request.getCarServices(), request.getClient().getCoordinates(), true);

    }

    public Person getFreeMastersByWorkshopId(Long id) {
        Optional<Person> freeMaster = personRepository.findByWorkPlaceId(id).stream().findAny().filter(Person::getStatus);
        if (freeMaster.isPresent()) {
            freeMaster.get().setStatus(false);
            personRepository.save(freeMaster.get());
        }
        return freeMaster.orElse(null);
    }
}
