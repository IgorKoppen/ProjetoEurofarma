package br.com.connectfy.EurofarmaCliente.dtos.quiz;

import br.com.connectfy.EurofarmaCliente.models.Answer;
import br.com.connectfy.EurofarmaCliente.models.Question;


public class AnswerDTO {

    private Long id;
    private String answer;
    private Boolean isCorrect;
    private QuestionDTO question;

    public AnswerDTO(Answer answer) {
        this.id = answer.getId();
        this.answer = answer.getAnswer();
        this.isCorrect = answer.getCorrect();
        if (answer.getQuestion() != null) {
            this.question = new QuestionDTO(answer.getQuestion());
        }
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

    public QuestionDTO getQuestion() {
        return question;
    }
}
