package ru.egorbarinov.questionnaire.dto.common;

import lombok.Data;
import ru.egorbarinov.questionnaire.dto.BaseDTO;

import java.util.UUID;

@Data
public class QuestionTypeDto implements BaseDTO {

  private UUID id;
  private String name;
}
