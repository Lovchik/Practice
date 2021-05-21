package by.vit.ban.repository;

import by.vit.ban.model.PricePosition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricePositionRepository extends CrudRepository<PricePosition,Long> {
}
