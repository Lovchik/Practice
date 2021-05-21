package by.vit.ban.service;

import by.vit.ban.model.Contract;
import by.vit.ban.model.Person;
import by.vit.ban.model.Request;
import by.vit.ban.model.Status;
import by.vit.ban.repository.ContractRepository;
import by.vit.ban.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContractService {
    private final ContractRepository contractRepository;
    private final WorkshopService workshopService;
    private final RequestRepository requestRepository;

    @Autowired
    public ContractService(ContractRepository contractRepository, WorkshopService workshopService, RequestRepository requestRepository) {
        this.contractRepository = contractRepository;
        this.workshopService = workshopService;
        this.requestRepository = requestRepository;
    }

    public boolean createContractFromRequest(Long id) {
        Optional<Request> request = requestRepository.findById(id);
        if (request.isPresent()) {
            Person master = workshopService.getFreeMastersByWorkshopId(request.get().getWorkshop().getId());

            if (master != null) {
                master.setStatus(false);
                Contract acceptedContract = new Contract(master, Status.ACCEPTED, request.get(), request.get().getWorkshop());
                contractRepository.save(acceptedContract);
                return true;
            }
            return false;
        }
        return false;
    }


}
