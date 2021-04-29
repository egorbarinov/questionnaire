package ru.egorbarinov.questionnaire.dto.test;

import lombok.Data;
import ru.egorbarinov.questionnaire.dto.BaseDTO;

import java.util.Set;
import java.util.UUID;

@Data
public class ChapterDto implements BaseDTO {

  private UUID id;
  private String name;
  private String description;
  private UUID testId;
  private Set<QuestionDto> questions;
}
