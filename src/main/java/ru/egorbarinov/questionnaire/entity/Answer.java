package ru.egorbarinov.questionnaire.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(schema = "questionnaire", name = "answer")
public class Answer implements BaseEntity {

  @Id
  @GeneratedValue
  @Type(type="pg-uuid")
  private UUID id;

  @NotNull
  @Column
  private String answer;

  @NotNull
  @Column(name = "deleted")
  private Boolean isDeleted = false;

  @NotNull
  @Column(name = "correct")
  private Boolean isCorrect = false;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "question_id")
  private Question question;
}
