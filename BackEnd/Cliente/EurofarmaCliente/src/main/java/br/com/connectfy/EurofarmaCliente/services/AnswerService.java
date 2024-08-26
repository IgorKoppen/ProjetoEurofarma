package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.dtos.quiz.AnswerDTO;
import br.com.connectfy.EurofarmaCliente.dtos.quiz.AnswerInsertDTO;
import br.com.connectfy.EurofarmaCliente.dtos.quiz.AnswerUpdateDTO;
import br.com.connectfy.EurofarmaCliente.exceptions.DatabaseException;
import br.com.connectfy.EurofarmaCliente.exceptions.ResourceNotFoundException;
import br.com.connectfy.EurofarmaCliente.models.Answer;
import br.com.connectfy.EurofarmaCliente.repositories.AnswerRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Transactional
    public List<AnswerDTO> insertAll(List<AnswerInsertDTO> answerInsertDTOs) {
        boolean atLeastOneCorrect = answerInsertDTOs.stream()
                .anyMatch(AnswerInsertDTO::isCorrect);

        if (!atLeastOneCorrect) {
            throw new IllegalArgumentException("É necessário informar pelo menos uma resposta correta");
        }

        List<AnswerDTO> answerDTOs = new ArrayList<>();

        for (AnswerInsertDTO answerInsertDTO : answerInsertDTOs) {
            Answer answer = new Answer(answerInsertDTO);
            answer = answerRepository.save(answer);
            answerDTOs.add(new AnswerDTO(answer));
        }

        return answerDTOs;
    }

    @Transactional
    public List<AnswerDTO> updateAll(List<AnswerUpdateDTO> answerUpdateDTOs) {
        List<AnswerDTO> updatedAnswers = new ArrayList<>();

        for (AnswerUpdateDTO answerUpdateDTO : answerUpdateDTOs) {
            // Verifica se uma resposta com o mesmo ID já existe no banco de dados
            if (answerRepository.existsById(answerUpdateDTO.id())) {
                continue; // Se existir, pula para o próximo
            }

            // Cria uma nova entidade Answer com os dados do DTO
            Answer answer = new Answer();
            answer.setId(answerUpdateDTO.id());
            answer.setAnswer(answerUpdateDTO.answer());
            answer.setCorrect(answerUpdateDTO.isCorrect());

            // Salva a nova entidade no banco de dados
            answer = answerRepository.save(answer);

            // Adiciona a resposta atualizada à lista de retorno
            updatedAnswers.add(new AnswerDTO(answer));
        }

        return updatedAnswers;
    }


    @Transactional
    public void delete(Long id) {
        if (!answerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Id invalido");
        }
        try {
            answerRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de inegridade referencial");
        }
    }

}