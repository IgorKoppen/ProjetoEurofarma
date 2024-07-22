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
    private TrainingService trainningService;

    @PostMapping
    public ResponseEntity<String> insertTrainning(@RequestBody @Valid TrainingCreationDTO trainningCreationDTO) {
        System.out.println(trainningCreationDTO);
        System.out.println(trainningCreationDTO);System.out.println(trainningCreationDTO);System.out.println(trainningCreationDTO);System.out.println(trainningCreationDTO);System.out.println(trainningCreationDTO);




        return trainningService.create(trainningCreationDTO);
    }

    @GetMapping
    public List<TrainingHistoricDTO> findAllTrainning(){
        return trainningService.findAll();
    }

    @GetMapping("/{id}")
    public TrainingHistoricDTO findTrainningById(@PathVariable Long id) {
        return trainningService.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTrainning(@PathVariable Long id, @RequestBody @Valid TrainingCreationDTO trainningCreationDTO) {
        return trainningService.update(id, trainningCreationDTO);
    }

    @PutMapping("/addEmployee")
    public ResponseEntity<?> addEmployee(@RequestBody UserConfirmAssinatureDTO userConfirmAssinatureDTO) {
        return trainningService.addEmployee(userConfirmAssinatureDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTrainning(@PathVariable Long id) {
        trainningService.delete(id);
    }

    @GetMapping("/getRoomByCode/{code}")
    public TrainingHistoricDTO getRoomByCode(@PathVariable  String code) {
        return trainningService.findByCode(code);
    }
    @GetMapping("/confirmPassword")
    public ResponseEntity<String> confirmPassword(@RequestParam Long userId,@RequestParam String code, @RequestParam String password) {
        return trainningService.confirmPassword(userId,code, password);
    }
   @GetMapping("/getAllRoomParticipants")
   public List<RoomParticipantsDTO> getAllRoomParticipants(@RequestParam Long roomId) {
       return trainningService.findAllRoomParticipants(roomId);
   }
}
