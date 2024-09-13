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
        // Busca o Quiz no repositório, lançando exceção se não encontrado
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum quiz encontrado com id " + id));

        // Atualiza os campos do quiz
        quiz.setNome(dto.nome());
        quiz.setDescription(dto.description());
        quiz.setNotaMinima(dto.notaMinima());
        quiz.setQuestionsNumber(dto.questionsNumber());

        // Verifica se o QuestionIdDTO contém uma lista de IDs de perguntas
        if (dto.questions() == null || dto.questions().id().isEmpty()) {
            quiz.setQuestions(new ArrayList<>());
        } else {
            // Extrai a lista de IDs das perguntas do QuestionIdDTO
            List<Long> questionIds = dto.questions().id();

            // Verifica se o número de questões corresponde ao número esperado
            if (questionIds.size() != dto.questionsNumber()) {
                throw new IllegalArgumentException("O número de questões fornecidas não corresponde ao número esperado de "
                        + dto.questionsNumber() + " questões.");
            }

            // Busca cada pergunta no repositório e cria uma lista de perguntas atualizadas
            List<Question> updatedQuestions = questionIds.stream()
                    .map(questionId -> questionRepository.findById(questionId)
                            .orElseThrow(() -> new ResourceNotFoundException("Nenhuma pergunta encontrada com id " + questionId)))
                    .collect(Collectors.toList());

            // Atualiza a lista de perguntas no quiz
            quiz.setQuestions(updatedQuestions);
        }

        // Salva o quiz atualizado
        quiz = quizRepository.save(quiz);

        // Retorna o DTO do quiz atualizado
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
