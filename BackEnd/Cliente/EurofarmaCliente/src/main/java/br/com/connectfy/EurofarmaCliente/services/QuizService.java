package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.dtos.quiz.QuizDTO;
import br.com.connectfy.EurofarmaCliente.dtos.quiz.QuizInsertDTO;
import br.com.connectfy.EurofarmaCliente.dtos.quiz.QuizUpdateDTO;
import br.com.connectfy.EurofarmaCliente.dtos.quiz.QuizValidateDTO;
import br.com.connectfy.EurofarmaCliente.exceptions.DatabaseException;
import br.com.connectfy.EurofarmaCliente.exceptions.ResourceNotFoundException;
import br.com.connectfy.EurofarmaCliente.models.Answer;
import br.com.connectfy.EurofarmaCliente.models.Quiz;
import br.com.connectfy.EurofarmaCliente.models.Question;
import br.com.connectfy.EurofarmaCliente.repositories.QuestionRepository;
import br.com.connectfy.EurofarmaCliente.repositories.QuizRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    @Transactional(readOnly = true)
    public Boolean validateQuizAnswers(Long quizId, QuizValidateDTO quizValidateBatchDTO) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));

        List<Long> questionIds = quizValidateBatchDTO.questionIds();
        List<String> userAnswers = quizValidateBatchDTO.userAnswers();

        if (questionIds.size() != userAnswers.size()) {
            throw new IllegalArgumentException("O número de questionIds e userAnswers deve ser igual.");
        }

        int correctAnswersCount = 0;

        for (int i = 0; i < questionIds.size(); i++) {
            Long questionId = questionIds.get(i);
            String userAnswer = userAnswers.get(i);

            Question question = questionRepository.findById(questionId)
                    .orElseThrow(() -> new ResourceNotFoundException("Question not found"));

            Optional<Answer> correctAnswer = question.getAnswers().stream()
                    .filter(Answer::getCorrect)
                    .findFirst();

            if (correctAnswer.isPresent() && correctAnswer.get().getAnswer().equals(userAnswer)) {
                correctAnswersCount++;
            }
        }

        int totalQuestions = quiz.getQuestions().size();
        int notaMinima = quiz.getNotaMinima();

        double userScore = ((double) correctAnswersCount / totalQuestions) * 10;

        return userScore >= notaMinima;
    }



}
