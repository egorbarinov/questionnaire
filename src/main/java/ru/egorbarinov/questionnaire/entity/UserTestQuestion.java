package ru.egorbarinov.questionnaire.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(schema = "questionnaire", name = "user_test_question")
public class UserTestQuestion implements BaseEntity {

    @Id
    @GeneratedValue
    @Type(type="pg-uuid")
    private UUID id;

    @Column(name = "user_answer")
    private String freeAnswer;

    @Column
    private LocalDateTime answered;

    @NotNull
    @Column(name = "correct")
    private Boolean isCorrect = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_test_id")
    private UserTest userTest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToMany
    @JoinTable(schema = "questionnaire",
            name = "selected_answer",
            joinColumns = @JoinColumn(name = "test_question_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_id"))
    private Set<Answer> answers;

    public UserTestQuestion() {
        answers = new HashSet<>();
    }

}
