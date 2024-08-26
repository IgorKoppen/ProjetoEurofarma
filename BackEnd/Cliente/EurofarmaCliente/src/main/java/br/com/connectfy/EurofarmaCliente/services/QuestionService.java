package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.repositories.QuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }



}
