package ru.egorbarinov.questionnaire.dto.manage;

import lombok.Data;
import ru.egorbarinov.questionnaire.dto.BaseDTO;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class QuestionFullDto implements BaseDTO {

  private UUID id;
  private String question;
  private UUID questionTypeId;
  private Set<AnswerFullDto> answers = new HashSet<>();
  private Boolean isDeleted;
  private Set<ChapterWrapDto> chapters;
}
