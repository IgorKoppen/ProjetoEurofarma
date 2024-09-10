package br.com.connectfy.EurofarmaCliente.models;

import br.com.connectfy.EurofarmaCliente.dtos.quiz.QuizDTO;
import br.com.connectfy.EurofarmaCliente.dtos.quiz.QuizInsertDTO;
import br.com.connectfy.EurofarmaCliente.dtos.quiz.QuizUpdateDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Integer notaMinima;
    @Column(nullable = false)
    private Integer questionsNumber;
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Training> trainings;
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Question> questions;

    public Quiz() {
    }

    public Quiz(QuizDTO quizDTO) {
        this.id = quizDTO.getId();
        this.nome = quizDTO.getNome();
        this.description = quizDTO.getDescription();
        this.notaMinima = quizDTO.getNotaMinima();
        this.questionsNumber = quizDTO.getQuestionsNumber();
        if(quizDTO.getQuestions() != null) {
            this.questions = quizDTO.getQuestions().stream().map(Question::new).collect(Collectors.toList());
        }
    }

    public Quiz(QuizInsertDTO quizInsertDTO) {
        this.nome = quizInsertDTO.nome();
        this.description = quizInsertDTO.description();
        this.notaMinima = quizInsertDTO.notaMinima();
        this.questionsNumber = quizInsertDTO.questionsNumber();
    }

    public Quiz(QuizUpdateDTO quizUpdateDTO) {
        this.nome = quizUpdateDTO.nome();
        this.description = quizUpdateDTO.description();
        this.notaMinima = quizUpdateDTO.notaMinima();
        this.questionsNumber = quizUpdateDTO.questionsNumber();
        this.questions = quizUpdateDTO.questions().stream().map(Question::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNotaMinima() {
        return notaMinima;
    }

    public void setNotaMinima(Integer notaMinima) {
        this.notaMinima = notaMinima;
    }

    public Integer getQuestionsNumber() {
        return questionsNumber;
    }

    public void setQuestionsNumber(Integer questionsNumber) {
        this.questionsNumber = questionsNumber;
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return Objects.equals(id, quiz.id) && Objects.equals(nome, quiz.nome) && Objects.equals(description, quiz.description) && Objects.equals(notaMinima, quiz.notaMinima) && Objects.equals(questionsNumber, quiz.questionsNumber) && Objects.equals(trainings, quiz.trainings) && Objects.equals(questions, quiz.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
