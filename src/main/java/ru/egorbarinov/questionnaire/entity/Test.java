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
@Table(name = "test")
public class Test implements BaseEntity {

    @Id
    @GeneratedValue
    @Type(type="pg-uuid")
    private UUID id;

    @NotNull
    @Column
    private String name;

    @Column
    private String description;

    @Column(updatable = false)
    private LocalDateTime created;

    @NotNull
    @Column
    private Short duration;

    @NotNull(message = "Field pass_score must not be null!")
    @Column(name = "pass_score")
    private Short passScore = 1;

    @NotNull
    @Column(name = "deleted")
    private Boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @OneToMany(mappedBy = "test",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Chapter> chapters;

    public Test() {
        chapters = new HashSet<>();
    }

    public void addChapter(Chapter chapter) {
        chapters.add(chapter);
        chapter.setTest(this);
    }

    public void removeChapter(Chapter chapter) {
        chapters.remove(chapter);
        chapter.setTest(null);
    }

    @Override
    public String toString() {
        return "Test{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", created=" + created +
            ", duration=" + duration +
            ", passScore=" + passScore +
            ", isDeleted=" + isDeleted +
            ", author=" + author +
            '}';
    }
}
