package by.vit.ban.service;

import by.vit.ban.model.CarService;
import by.vit.ban.model.Person;
import by.vit.ban.model.Request;
import by.vit.ban.model.Workshop;
import by.vit.ban.repository.CarServiceRepository;
import by.vit.ban.repository.PersonRepository;
import by.vit.ban.repository.RequestRepository;
import by.vit.ban.repository.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequestService {
    private final RequestRepository requestRepository;
    private final PersonRepository personRepository;
    private final WorkshopRepository workshopRepository;
    private final CarServiceRepository carServiceRepository;
    @Autowired
    public RequestService(RequestRepository requestRepository,PersonRepository personRepository,WorkshopRepository workshopRepository,CarServiceRepository carServiceRepository) {
        this.requestRepository = requestRepository;
        this.personRepository= personRepository;
        this.workshopRepository= workshopRepository;
        this.carServiceRepository = carServiceRepository;
    }

    public void save(Request request){
        Request fullRequest = new Request();
        List<CarService> carServiceList = new ArrayList<>();
        for (CarService carService:request.getCarServices()) {
            carServiceList.add(carServiceRepository.getCarServiceByName(carService.getName()));
        }
        fullRequest.setCarServices(carServiceList);
        Optional<Person> person = personRepository.getPersonByPasswordAndPhoneNumber("123321","+375297537543");
        person.ifPresent(fullRequest::setClient);
        Optional<Workshop> workshop = workshopRepository.findById(request.getWorkshop().getId());
        workshop.ifPresent(fullRequest::setWorkshop);
        requestRepository.save(fullRequest);
    }

    public List<Request> getWorkshopsRequestsWithoutContract(Long workshopId){
        List<Request> requestList = requestRepository.getRequestByWorkshop_Id(workshopId);
        return requestList.stream().filter(request -> request.getContract()==null).collect(Collectors
                .toCollection(ArrayList::new));
    }
}
