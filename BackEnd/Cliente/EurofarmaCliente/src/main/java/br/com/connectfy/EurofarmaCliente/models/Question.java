package br.com.connectfy.EurofarmaCliente.models;

import br.com.connectfy.EurofarmaCliente.dtos.quiz.QuestionDTO;
import br.com.connectfy.EurofarmaCliente.dtos.quiz.QuestionInsertDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
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
    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

    public Question() {
    }

    public Question(QuestionDTO questionDTO) {
        this.id = questionDTO.getId();
        this.question = questionDTO.getQuestion();
        this.quiz = new Quiz(questionDTO.getQuiz());
        if(questionDTO.getAnswers() != null) {
            this.answers = questionDTO.getAnswers().stream().map(Answer::new).collect(Collectors.toList());
        }
    }

    public Question(QuestionInsertDTO questionInsertDTO) {
        this.question = questionInsertDTO.question();
        this.quiz = new Quiz(questionInsertDTO.quizDTO());
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
