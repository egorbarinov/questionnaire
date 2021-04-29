package ru.egorbarinov.questionnaire.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(schema = "questionnaire", name = "chapter")
public class Chapter implements BaseEntity {

    @Id
    @GeneratedValue
    @Type(type="pg-uuid")
    private UUID id;

    @NotNull
    @Column
    private String name;

    @Column
    private String description;

    @NotNull
    @Column(name = "deleted")
    private Boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private Test test;

    @ManyToMany
    @JoinTable(schema = "questionnaire",
        name = "question_chapter",
        joinColumns = @JoinColumn(name = "chapter_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    private Set<Question> questions;

    public Chapter() {
        questions = new HashSet<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
        if (!question.hasChapters(this)) {
            question.addChapter(this);
        }
    }

    public void removeQuestion(Question question) {
        questions.remove(question);
        if (question.hasChapters(this)) {
            question.removeChapter(this);
        }
    }

    boolean hasQuestion(Question question) {
        return questions.contains(question);
    }

    @Override
    public String toString() {
        return "Chapter{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", isDeleted=" + isDeleted +
            ", test=" + test +
            '}';
    }
}
