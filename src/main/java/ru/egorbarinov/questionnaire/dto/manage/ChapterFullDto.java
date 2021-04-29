package ru.egorbarinov.questionnaire.dto.manage;

import lombok.Data;
import ru.egorbarinov.questionnaire.dto.BaseDTO;

import java.util.Set;
import java.util.UUID;

@Data
public class ChapterFullDto implements BaseDTO {

  private UUID id;
  private String name;
  private String description;
  private Boolean isDeleted;
  private UUID testId;
  private Set<QuestionFullDto> questions;
}
