package br.com.connectfy.EurofarmaCliente.controllers;

import br.com.connectfy.EurofarmaCliente.dtos.EmployeeDTO;
import br.com.connectfy.EurofarmaCliente.dtos.TrainningDTO;
import br.com.connectfy.EurofarmaCliente.dtos.TrainningHistoricDTO;
import br.com.connectfy.EurofarmaCliente.services.TrainningService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eurofarma/trainning")
public class TrainningController {

    @Autowired
    private TrainningService trainningService;

    @PostMapping
    public ResponseEntity<String> insertTrainning(@RequestBody @Valid TrainningDTO trainningDTO) {
        return trainningService.create(trainningDTO);
    }

    @GetMapping
    public List<TrainningHistoricDTO> findAllTrainning(){
        return trainningService.findAll();
    }

    @GetMapping("/{id}")
    public TrainningHistoricDTO findTrainningById(@PathVariable Long id) {
        return trainningService.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTrainning(@PathVariable Long id, @RequestBody @Valid TrainningDTO trainningDTO) {
        return trainningService.update(id, trainningDTO);
    }

    @PutMapping("/addEmployee/{code}/{password}")
    public ResponseEntity<?> addEmployee(@PathVariable String code,@PathVariable String password, @RequestBody @Valid EmployeeDTO employeeDTO) {
        return trainningService.addEmployee(code,password, employeeDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTrainning(@PathVariable Long id) {
        trainningService.delete(id);
    }

}
