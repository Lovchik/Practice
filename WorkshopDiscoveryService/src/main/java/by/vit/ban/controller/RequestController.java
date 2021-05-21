package by.vit.ban.controller;

import by.vit.ban.model.Request;
import by.vit.ban.repository.RequestRepository;
import by.vit.ban.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/requests")
public class RequestController {
    private final RequestRepository requestRepository;
    private final RequestService requestService;

    @Autowired
    public RequestController(RequestRepository requestRepository, RequestService requestService) {
        this.requestRepository = requestRepository;
        this.requestService = requestService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<Request> getRequests() {
        return (List<Request>) requestRepository.findAll();
    }

    @GetMapping("/workshop/{workshopId}")
    public List<Request> getWorkshopsRequest(@PathVariable(name = "workshopId") Long id) {
        return requestService.getWorkshopsRequestsWithoutContract(id);
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<Request> getRequestById(@PathVariable(name = "requestId") Long id) {
        Optional<Request> repositoryById = requestRepository.findById(id);
        return repositoryById.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> saveRequest(@RequestBody Request request) {
        requestService.save(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{requestId}")
    public ResponseEntity<Void> deleteRequestById(@PathVariable(name = "requestId") Long id) {
        if (requestRepository.findById(id).isPresent()) {
            requestRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(207).build();
    }

    @GetMapping("/person/{clientID}")
    public List<Request> getClientsRequests(@PathVariable(name = "clientID") Long id) {
        return requestRepository.getRequestByClient_Id(id);
    }
}
