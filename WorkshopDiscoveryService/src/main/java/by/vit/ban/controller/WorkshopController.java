package by.vit.ban.controller;

import by.vit.ban.model.Person;
import by.vit.ban.model.Request;
import by.vit.ban.model.Workshop;
import by.vit.ban.repository.WorkshopRepository;
import by.vit.ban.service.WorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/workshop")
public class WorkshopController {
    private final WorkshopRepository workshopRepository;
    private final WorkshopService workshopService;

    @Autowired
    public WorkshopController(WorkshopRepository workshopRepository, WorkshopService workshopService) {
        this.workshopRepository = workshopRepository;
        this.workshopService = workshopService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<Workshop> getWorkshops() {
        return (List<Workshop>) workshopRepository.findAll();
    }

    @GetMapping("/{workshopId}")
    public ResponseEntity<Workshop> getWorkshopById(@PathVariable(name = "workshopId") Long id) {
        Optional<Workshop> workshopOptional = workshopRepository.findById(id);
        return workshopOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Workshop getWorkshopByCarServicesAndCoords(@RequestBody Request request) {
        return workshopService.getWorkshopNearby(request);
    }

    @PostMapping("/cheaper")
    public Workshop getCheaperWorkshop(@RequestBody Request request) {
        return workshopService.getCheaperWorkshop(request);
    }



    @GetMapping("/master/{workshopId}")
    public Person getFreeMasters(@PathVariable(name = "workshopId") Long id){
        return workshopService.getFreeMastersByWorkshopId(id);
    }
}

