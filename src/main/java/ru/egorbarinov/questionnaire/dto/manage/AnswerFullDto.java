package ru.egorbarinov.questionnaire.dto.manage;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.egorbarinov.questionnaire.dto.BaseDTO;
import ru.egorbarinov.questionnaire.dto.test.AnswerDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class AnswerFullDto extends AnswerDto implements BaseDTO {

  private Boolean isCorrect;
  private Boolean isDeleted;
}
