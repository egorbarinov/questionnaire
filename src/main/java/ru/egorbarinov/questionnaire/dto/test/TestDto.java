package ru.egorbarinov.questionnaire.dto.test;

import lombok.Data;
import ru.egorbarinov.questionnaire.dto.BaseDTO;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
public class TestDto implements BaseDTO {

  private UUID id;
  private String name;
  private String description;
  private LocalDateTime created;
  private Short duration;
  private UUID authorId;
  private Set<ChapterDto> chapters;
  private Short passScore;
}
