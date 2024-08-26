package br.com.connectfy.EurofarmaCliente.controllers;

import br.com.connectfy.EurofarmaCliente.dtos.quiz.AnswerDTO;
import br.com.connectfy.EurofarmaCliente.services.AnswerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eurofarma/answer")
@Tag(name = "Answer", description = "Controller Answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

//    public ResponseEntity<AnswerDTO>

}
