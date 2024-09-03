package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.dtos.quiz.QuestionDTO;
import br.com.connectfy.EurofarmaCliente.dtos.quiz.QuestionInsertDTO;
import br.com.connectfy.EurofarmaCliente.exceptions.DatabaseException;
import br.com.connectfy.EurofarmaCliente.exceptions.ResourceNotFoundException;
import br.com.connectfy.EurofarmaCliente.models.Answer;
import br.com.connectfy.EurofarmaCliente.models.Question;
import br.com.connectfy.EurofarmaCliente.models.Quiz;
import br.com.connectfy.EurofarmaCliente.repositories.AnswerRepository;
import br.com.connectfy.EurofarmaCliente.repositories.QuestionRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public QuestionService(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @Transactional(readOnly = true)
    public QuestionDTO findById(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhuma questão encontrada"));
        return new QuestionDTO(question);
    }

    @Transactional
    public QuestionDTO insert(QuestionInsertDTO questionInsertDTO, List<Long> answerIds) {
        List<Answer> answers = answerRepository.findAllById(answerIds);

        if (answers.size() != answerIds.size()) {
            throw new IllegalArgumentException("Um ou mais IDs de respostas são inválidos.");
        }

        Question question = new Question(questionInsertDTO);

        question.setAnswers(new ArrayList<>(answers));

        question = questionRepository.save(question);

        return new QuestionDTO(question);
    }

    @Transactional
    public QuestionDTO update(Long questionId, QuestionInsertDTO questionInsertDTO, List<Long> answerIds) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("Pergunta não encontrada para o ID fornecido."));

        List<Answer> answers = answerRepository.findAllById(answerIds);

        if (answers.size() != answerIds.size()) {
            throw new IllegalArgumentException("Um ou mais IDs de respostas são inválidos.");
        }

        question.setQuestion(questionInsertDTO.question());
        question.setQuiz(new Quiz(questionInsertDTO.quizDTO()));

        question.setAnswers(new ArrayList<>(answers));

        question = questionRepository.save(question);

        return new QuestionDTO(question);
    }


    @Transactional
    public void delete(Long id) {
        if(!questionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Questão não encontrada.");
        }
        try {
            questionRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de inegridade referencial");
        }
    }


}
