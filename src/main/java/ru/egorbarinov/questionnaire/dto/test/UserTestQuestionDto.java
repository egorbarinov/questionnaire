package ru.egorbarinov.questionnaire.dto.test;

import lombok.Data;
import ru.egorbarinov.questionnaire.dto.BaseDTO;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
public class UserTestQuestionDto implements BaseDTO {

  private UUID id;
  private String freeAnswer;
  private LocalDateTime answered;
  private Boolean isCorrect;
  private UUID userTestId;
  private UUID questionId;
  private Set<AnswerDto> answers;
}
