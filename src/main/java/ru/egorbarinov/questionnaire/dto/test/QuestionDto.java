package ru.egorbarinov.questionnaire.dto.test;

import lombok.Data;
import ru.egorbarinov.questionnaire.dto.BaseDTO;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class QuestionDto implements BaseDTO {

  private UUID id;
  private String question;
  private UUID questionTypeId;
  private Set<AnswerDto> answers = new HashSet<>();
}
