package br.com.connectfy.EurofarmaCliente.dtos.quiz;

import br.com.connectfy.EurofarmaCliente.models.Answer;
import br.com.connectfy.EurofarmaCliente.models.Question;
import br.com.connectfy.EurofarmaCliente.models.Quiz;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public class QuestionDTO {



    private final Long id;
    @NotBlank(message = "É necessário uma pergunta")
    private final String question;
    private final List<AnswerDTO> answers = new ArrayList<>();

    public QuestionDTO(Long id, String question) {
        this.id = id;
        this.question = question;
    }

    public QuestionDTO(Question question) {
        this.id = question.getId();
        this.question = question.getQuestion();
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


    public List<AnswerDTO> getAnswers() {
        return answers;
    }
}
