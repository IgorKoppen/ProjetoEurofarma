package br.com.connectfy.EurofarmaCliente.dtos.quiz;

import br.com.connectfy.EurofarmaCliente.dtos.training.TrainingDTO;
import br.com.connectfy.EurofarmaCliente.models.Quiz;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


import java.util.ArrayList;
import java.util.List;

public class QuizDTO {

    private final Long id;
    @NotBlank(message = "É necessário um nome")
    private final String nome;
    @NotBlank(message = "É necessário uma descrição")
    private final String description;
    @NotBlank(message = "É necessário uma nota mínima")
    private final Integer notaMinima;
    @NotNull(message = "É necessário informar o número de questões")
    @Positive(message = "O valor deve ser positivo")
    private final Integer questionsNumber;

    private List<TrainingDTO> trainings = new ArrayList<>();

    private List<QuestionDTO> questions = new ArrayList<>();

    public QuizDTO(Quiz quiz) {
        this.id = quiz.getId();
        this.nome = quiz.getNome();
        this.description = quiz.getDescription();
        this.notaMinima = quiz.getNotaMinima();
        this.questionsNumber = quiz.getQuestionsNumber();
        if (quiz.getQuestions() != null) {
            this.questions = quiz.getQuestions().stream().map(QuestionDTO::new).toList();
        }
        if (quiz.getTrainings() != null) {
            this.trainings = quiz.getTrainings().stream().map(TrainingDTO::new).toList();
        }
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescription() {
        return description;
    }

    public Integer getNotaMinima() {
        return notaMinima;
    }

    public Integer getQuestionsNumber() {
        return questionsNumber;
    }

    public List<TrainingDTO> getTrainings() {
        return trainings;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }
}