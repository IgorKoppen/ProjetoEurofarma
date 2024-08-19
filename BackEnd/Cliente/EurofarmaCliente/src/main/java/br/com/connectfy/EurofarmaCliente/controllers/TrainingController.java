package br.com.connectfy.EurofarmaCliente.controllers;

import br.com.connectfy.EurofarmaCliente.dtos.*;
import br.com.connectfy.EurofarmaCliente.dtos.training.TrainingDTO;
import br.com.connectfy.EurofarmaCliente.dtos.training.TrainingInsertDTO;
import br.com.connectfy.EurofarmaCliente.dtos.training.TrainingOfEmployeeDTO;
import br.com.connectfy.EurofarmaCliente.dtos.training.TrainingWithEmployeesInfo;
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
        public ResponseEntity<TrainingDTO> insert(@RequestBody @Valid TrainingInsertDTO trainingCreationDTO) {
        TrainingDTO  trainingDTO = trainingService.insert(trainingCreationDTO);
        return ResponseEntity.ok(trainingDTO);
    }

    @GetMapping
    public ResponseEntity<List<TrainingDTO>> findAll(){
         List<TrainingDTO> trainingDTOList = trainingService.findAll();
        return ResponseEntity.ok(trainingDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingDTO> findById(@PathVariable Long id) {
        TrainingDTO trainingDTO = trainingService.findById(id);
        return ResponseEntity.ok(trainingDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingDTO> update(@PathVariable Long id, @RequestBody @Valid TrainingInsertDTO trainingCreationDTO) {
        TrainingDTO  trainingDTO = trainingService.update(id, trainingCreationDTO);
        return ResponseEntity.ok(trainingDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        trainingService.cancelTraining(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getRoomByCode/{code}")
    public ResponseEntity<TrainingDTO> findByCode(@PathVariable  String code) {
        TrainingDTO trainingDTO = trainingService.findByCode(code);
        return ResponseEntity.ok(trainingDTO);
    }
    @GetMapping(value = "/EmployeeTrainings/{id}")
    public ResponseEntity<List<TrainingOfEmployeeDTO>> findEmployeeTrainingsByEmployeeId(@PathVariable Long id) {
        List<TrainingOfEmployeeDTO> dto = trainingService.findEmployeeTrainingsById(id);
        return ResponseEntity.ok(dto);
    }
    @GetMapping("/InstructorTrainings/{id}")
    public ResponseEntity<List<TrainingWithEmployeesInfo>> findInstructorTrainingsByInstructorId(@PathVariable Long id) {
        List<TrainingWithEmployeesInfo> dto = trainingService.findInstructorTrainingsById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/confirmPassword")
    public ResponseEntity<Void> confirmPassword(@RequestParam Long userId,@RequestParam String code, @RequestParam String password) {
         trainingService.confirmPassword(userId,code, password);
         return ResponseEntity.noContent().build();
    }
   @GetMapping("/getAllRoomParticipants/{roomId}")
   public ResponseEntity<List<RoomParticipantsDTO>> getAllRoomParticipants(@PathVariable Long roomId) {
        List<RoomParticipantsDTO> roomParticipantsDTOS = trainingService.findAllRoomParticipants(roomId);
       return ResponseEntity.ok(roomParticipantsDTOS);
   }
    @PutMapping("/addEmployee")
    public ResponseEntity<Void> addEmployeeIntoTraining(@RequestBody UserConfirmAssinatureDTO userConfirmAssinatureDTO) {
        trainingService.addEmployeeInTraining(userConfirmAssinatureDTO);
        return ResponseEntity.noContent().build();
    }
}
