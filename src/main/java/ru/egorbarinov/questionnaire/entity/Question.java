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
@Table(schema = "questionnaire", name = "question")
public class Question implements BaseEntity {

    @Id
    @GeneratedValue
    @Type(type="pg-uuid")
    private UUID id;

    @NotNull
    @Column
    String question;

    @NotNull
    @Column(name = "deleted")
    Boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_type_id")
    QuestionType questionType;

    @OneToMany(mappedBy = "question",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Answer> answers;

    @ManyToMany(mappedBy = "questions")
    Set<Chapter> chapters;

    public Question() {
        answers = new HashSet<>();
    }

    public void addChapter(Chapter chapter) {
        chapters.add(chapter);
        if (!chapter.hasQuestion(this)) {
            chapter.addQuestion(this);
        }
    }

    public void removeChapter(Chapter chapter) {
        chapters.remove(chapter);
        if (chapter.hasQuestion(this)) {
            chapter.removeQuestion(this);
        }
    }

    boolean hasChapters(Chapter chapter) {
        return chapters.contains(chapter);
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
        answer.setQuestion(this);
    }

    public void removeAnswer(Answer answer) {
        answers.remove(answer);
        answer.setQuestion(null);
    }

    @Override
    public String toString() {
        return "Question{" +
            "id=" + id +
            ", question='" + question + '\'' +
            ", isDeleted=" + isDeleted +
            ", questionType=" + questionType +
            ", answers=" + answers +
            ", chapters=" + chapters +
            '}';
    }
}
