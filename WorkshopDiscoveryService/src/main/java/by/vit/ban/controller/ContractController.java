package by.vit.ban.controller;

import by.vit.ban.model.Contract;
import by.vit.ban.repository.ContractRepository;
import by.vit.ban.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/contract")
public class ContractController {

    private final ContractRepository contractRepository;
    private final ContractService contractService;

    @Autowired
    public ContractController(ContractRepository contractRepository,ContractService contractService) {
        this.contractRepository = contractRepository;
        this.contractService = contractService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/master/{masterId}")
    public List<Contract> getMastersContract(@PathVariable(name = "masterId") Long id) {
        return contractRepository.getContractsByMaster_Id(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/workshop/{workshopId}")
    public List<Contract> getWorkshopsContract(@PathVariable(name = "workshopId") Long id) {
        return contractRepository.getContractsByWorkshop_Id(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/client/{clientId}")
    public List<Contract> getClientsContract(@PathVariable(name = "clientId") Long id) {
        return contractRepository.getContractByRequestClientId(id);
    }

    @PostMapping("/request/{requestId}")
    public ResponseEntity<Void> createContract(@PathVariable(name = "requestId") Long id) {
        if(contractService.createContractFromRequest(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }
}
