package br.com.connectfy.EurofarmaCliente.dtos.quiz;

import br.com.connectfy.EurofarmaCliente.models.Answer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class AnswerDTO {

    private final Long id;
    @NotBlank(message = "É necessário uma resposta")
    private final String answer;
    @NotNull(message = "É necessário informar se a questão é correta")
    private final Boolean isCorrect;
    private final Long questionId;

    public AnswerDTO(Answer answer) {
        this.id = answer.getId();
        this.answer = answer.getAnswer();
        this.isCorrect = answer.getCorrect();
        this.questionId = (answer.getQuestion() != null) ? answer.getQuestion().getId() : null;
    }

    public Long getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public Long getQuestion() {
        return questionId;
    }
}
