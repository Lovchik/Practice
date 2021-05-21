package by.vit.ban.repository;

import by.vit.ban.model.Workshop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkshopRepository extends CrudRepository<Workshop, Long> {
}
