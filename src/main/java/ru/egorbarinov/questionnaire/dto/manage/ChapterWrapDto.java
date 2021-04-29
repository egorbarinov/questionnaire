package ru.egorbarinov.questionnaire.dto.manage;

import lombok.Data;
import ru.egorbarinov.questionnaire.dto.BaseDTO;

import java.util.UUID;
@Data
public class ChapterWrapDto implements BaseDTO {

  private UUID id;
  private String name;
  private String description;
}
