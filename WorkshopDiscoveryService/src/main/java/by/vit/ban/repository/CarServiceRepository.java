package by.vit.ban.repository;

import by.vit.ban.model.CarService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarServiceRepository extends CrudRepository<CarService, Long> {
    CarService getCarServiceByName(String name);
}
