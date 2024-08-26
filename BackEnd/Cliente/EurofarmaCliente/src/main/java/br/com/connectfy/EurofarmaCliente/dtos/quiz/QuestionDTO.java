package br.com.connectfy.EurofarmaCliente.dtos.quiz;

import br.com.connectfy.EurofarmaCliente.models.Answer;
import br.com.connectfy.EurofarmaCliente.models.Question;
import br.com.connectfy.EurofarmaCliente.models.Quiz;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class QuestionDTO {

    private Long id;
    private String question;
    private String description;
    private QuizDTO quiz;
    private List<AnswerDTO> answers = new ArrayList<>();

    public QuestionDTO(Question question) {
        this.id = question.getId();
        this.question = question.getQuestion();
        this.description = question.getDescription();
        if (question.getQuiz() != null) {
            this.quiz = new QuizDTO(question.getQuiz());
        }
        if (question.getAnswers() != null) {
            question.getAnswers().forEach(answerDTO -> {this.answers.add(new AnswerDTO(answerDTO));});
        }
    }

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getDescription() {
        return description;
    }

    public QuizDTO getQuiz() {
        return quiz;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }
}
