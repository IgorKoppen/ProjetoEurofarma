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
import br.com.connectfy.EurofarmaCliente.repositories.QuizRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final QuizRepository quizRepository;

    public QuestionService(QuestionRepository questionRepository, AnswerRepository answerRepository, QuizRepository quizRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.quizRepository = quizRepository;
    }

    @Transactional(readOnly = true)
    public QuestionDTO findById(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhuma questão encontrada"));
        return new QuestionDTO(question);
    }

    @Transactional
    public QuestionDTO insert(QuestionInsertDTO dto) {
        // Busca o quiz pelo ID fornecido
        Quiz quiz = quizRepository.findById(dto.quizId())
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum quiz encontrado com id " + dto.quizId()));

        // Busca as respostas pelos IDs fornecidos
        List<Answer> answers = answerRepository.findAllById(dto.answerIds());

        if (answers.size() != dto.answerIds().size()) {
            throw new IllegalArgumentException("Um ou mais IDs de respostas são inválidos.");
        }

        // Cria uma nova pergunta e vincula ao quiz
        Question question = new Question();
        question.setQuestion(dto.question());
        question.setQuiz(quiz);

        // Define as respostas associadas à pergunta
        question.setAnswers(new ArrayList<>(answers));

        // Salva a pergunta no banco de dados
        question = questionRepository.save(question);

        return new QuestionDTO(question);
    }



    @Transactional
    public QuestionDTO update(Long questionId, QuestionInsertDTO questionInsertDTO, List<Long> answerIds) {
        // Busca a pergunta pelo ID
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("Pergunta não encontrada para o ID fornecido."));

        // Busca as respostas pelos IDs fornecidos
        List<Answer> answers = answerRepository.findAllById(answerIds);

        if (answers.size() != answerIds.size()) {
            throw new IllegalArgumentException("Um ou mais IDs de respostas são inválidos.");
        }

        // Atualiza os campos da pergunta
        question.setQuestion(questionInsertDTO.question());

        // Busca o quiz pelo ID
        Quiz quiz = quizRepository.findById(questionInsertDTO.quizId())
                .orElseThrow(() -> new IllegalArgumentException("Quiz não encontrado para o ID fornecido."));
        question.setQuiz(quiz);

        // Define as respostas associadas à pergunta
        question.setAnswers(new ArrayList<>(answers));

        // Salva a pergunta atualizada
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
