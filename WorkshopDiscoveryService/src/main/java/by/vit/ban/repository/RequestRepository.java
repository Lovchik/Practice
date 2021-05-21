package by.vit.ban.repository;

import by.vit.ban.model.Request;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends CrudRepository<Request, Long> {
    List<Request> getRequestByWorkshop_Id(Long idWorkshop);
    List<Request> getRequestByClient_Id(Long clientId);
}
