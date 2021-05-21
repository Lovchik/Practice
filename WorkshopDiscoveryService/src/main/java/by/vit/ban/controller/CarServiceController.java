package by.vit.ban.controller;

import by.vit.ban.model.CarService;
import by.vit.ban.repository.CarServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/carServices")
public class CarServiceController {
    private final CarServiceRepository carServiceRepository;

    @Autowired
    public CarServiceController(CarServiceRepository carServiceRepository) {
        this.carServiceRepository = carServiceRepository;
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<CarService> getCarServices() {
        return (List<CarService>) carServiceRepository.findAll();
    }
}
