package ru.egorbarinov.questionnaire.dto.manage;

import lombok.Data;
import ru.egorbarinov.questionnaire.dto.BaseDTO;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
public class TestFullDto implements BaseDTO {

  private UUID id;
  private String name;
  private String description;
  private LocalDateTime created;
  private Short duration;
  private Boolean isDeleted;
  private UUID authorId;
  private Set<ChapterFullDto> chapters;
  private Short passScore;
}
