package by.vit.ban.repository;

import by.vit.ban.model.Contract;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends CrudRepository<Contract, Long> {
    List<Contract> getContractsByMaster_Id(Long idMaster);
    List<Contract> getContractsByWorkshop_Id(Long idWorkshop);
    List<Contract> getContractByRequestClientId(Long clientId);

}
