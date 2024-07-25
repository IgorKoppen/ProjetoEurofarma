package br.com.connectfy.EurofarmaCliente.controllers;

import br.com.connectfy.EurofarmaCliente.dtos.*;
import br.com.connectfy.EurofarmaCliente.services.TrainingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eurofarma/training")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    @PostMapping
    public ResponseEntity<String> insertTraining(@RequestBody @Valid TrainingCreationDTO trainingCreationDTO) {
        return trainingService.create(trainingCreationDTO);
    }

    @GetMapping
    public List<TrainingHistoricDTO> findAllTraining(){
        return trainingService.findAll();
    }

    @GetMapping("/{id}")
    public TrainingHistoricDTO findTrainingById(@PathVariable Long id) {
        return trainingService.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTraining(@PathVariable Long id, @RequestBody @Valid TrainingCreationDTO trainingCreationDTO) {
        return trainingService.update(id, trainingCreationDTO);
    }

    @PutMapping("/addEmployee")
    public ResponseEntity<?> addEmployee(@RequestBody UserConfirmAssinatureDTO userConfirmAssinatureDTO) {
        return trainingService.addEmployee(userConfirmAssinatureDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTrainning(@PathVariable Long id) {
        trainingService.delete(id);
    }

    @GetMapping("/getRoomByCode/{code}")
    public TrainingHistoricDTO getRoomByCode(@PathVariable  String code) {
        return trainingService.findByCode(code);
    }
    @GetMapping("/confirmPassword")
    public ResponseEntity<String> confirmPassword(@RequestParam Long userId,@RequestParam String code, @RequestParam String password) {
        return trainingService.confirmPassword(userId,code, password);
    }
   @GetMapping("/getAllRoomParticipants")
   public List<RoomParticipantsDTO> getAllRoomParticipants(@RequestParam Long roomId) {
       return trainingService.findAllRoomParticipants(roomId);
   }
}