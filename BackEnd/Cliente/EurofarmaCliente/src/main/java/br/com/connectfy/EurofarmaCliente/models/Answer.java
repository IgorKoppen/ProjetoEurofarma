package br.com.connectfy.EurofarmaCliente.models;

import br.com.connectfy.EurofarmaCliente.dtos.quiz.AnswerDTO;
import br.com.connectfy.EurofarmaCliente.dtos.quiz.AnswerInsertDTO;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String answer;
    @Column(nullable = false)
    private Boolean isCorrect;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    public Answer() {
    }

    public Answer(AnswerDTO answerDTO) {
        this.id = answerDTO.getId();
        this.answer = answerDTO.getAnswer();
        this.isCorrect = answerDTO.getCorrect();
        this.question = new Question(answerDTO.getQuestion());
    }

    public Answer(AnswerInsertDTO answerInsertDTO) {
        this.answer = answerInsertDTO.answer();
        this.isCorrect = answerInsertDTO.isCorrect();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer1 = (Answer) o;
        return Objects.equals(id, answer1.id) && Objects.equals(answer, answer1.answer) && Objects.equals(isCorrect, answer1.isCorrect) && Objects.equals(question, answer1.question);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
