package br.com.connectfy.EurofarmaCliente.models;

import br.com.connectfy.EurofarmaCliente.dtos.question.QuestionDTO;
import br.com.connectfy.EurofarmaCliente.dtos.question.QuestionIdDTO;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String question;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;
    @OneToMany(mappedBy = "question", orphanRemoval = true)
    private List<Answer> answers;

    public Question() {
    }

    public Question(QuestionDTO questionDTO) {
        this.id = questionDTO.getId();
        this.question = questionDTO.getQuestion();
        if(questionDTO.getAnswers() != null) {
            this.answers = questionDTO.getAnswers().stream().map(Answer::new).collect(Collectors.toList());
        }
    }

    public Question(QuestionIdDTO questionIdDTO) {
    }

    public Question(Long aLong) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return Objects.equals(id, question1.id) && Objects.equals(question, question1.question) && Objects.equals(quiz, question1.quiz) && Objects.equals(answers, question1.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
