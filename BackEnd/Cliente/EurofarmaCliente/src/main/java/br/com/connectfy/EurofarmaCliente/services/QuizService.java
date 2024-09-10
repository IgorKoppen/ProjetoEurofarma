package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.dtos.quiz.QuizDTO;
import br.com.connectfy.EurofarmaCliente.dtos.quiz.QuizInsertDTO;
import br.com.connectfy.EurofarmaCliente.dtos.quiz.QuizUpdateDTO;
import br.com.connectfy.EurofarmaCliente.exceptions.DatabaseException;
import br.com.connectfy.EurofarmaCliente.exceptions.ResourceNotFoundException;
import br.com.connectfy.EurofarmaCliente.models.Quiz;
import br.com.connectfy.EurofarmaCliente.models.Question;
import br.com.connectfy.EurofarmaCliente.repositories.QuestionRepository;
import br.com.connectfy.EurofarmaCliente.repositories.QuizRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    private final QuestionRepository questionRepository;

    public QuizService(QuizRepository quizRepository, QuestionRepository questionRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
    }

    @Transactional(readOnly = true)
    public List<QuizDTO> findAll() {
        List<Quiz> quizList = quizRepository.findAll();
        return quizList.stream().map(QuizDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public QuizDTO findById(Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum quiz encontrado com id" + id));
        return new QuizDTO(quiz);
    }

    @Transactional
    public QuizDTO insert(QuizInsertDTO dto) {
        Quiz quiz = new Quiz(dto);
        quiz = quizRepository.save(quiz);
        return new QuizDTO(quiz);
    }

    @Transactional
    public QuizDTO update(Long id, QuizUpdateDTO dto) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum quiz encontrado com id " + id));

        quiz.setNome(dto.nome());
        quiz.setDescription(dto.description());
        quiz.setNotaMinima(dto.notaMinima());
        quiz.setQuestionsNumber(dto.questionsNumber());

        if (dto.questions() == null || dto.questions().isEmpty()) {
            quiz.setQuestions(new ArrayList<>());
        } else {
            // Supondo que você tenha apenas um objeto QuestionIdListDTO na lista
            List<Long> questionIds = dto.questions().stream()
                    .flatMap(q -> q.id().stream())
                    .toList();

            List<Question> updatedQuestions = questionIds.stream()
                    .map(questionId -> questionRepository.findById(questionId)
                            .orElseThrow(() -> new ResourceNotFoundException("Nenhuma pergunta encontrada com id " + questionId)))
                    .collect(Collectors.toList());

            quiz.setQuestions(updatedQuestions);
        }

        quiz = quizRepository.save(quiz);

        return new QuizDTO(quiz);
    }




    @Transactional
    public void delete(Long id) {
        if (!quizRepository.existsById(id)) {
            throw new ResourceNotFoundException("Nenhum quiz encontrado com id: " + id);
        }
        try {
            quizRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de inegridade referencial");
        }
    }

}
