package ru.egorbarinov.questionnaire.mapper.impl.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.egorbarinov.questionnaire.dto.manage.ChapterWrapDto;
import ru.egorbarinov.questionnaire.entity.Chapter;
import ru.egorbarinov.questionnaire.mapper.impl.AbstractMapper;
import ru.egorbarinov.questionnaire.repository.ChapterDao;

import java.util.Optional;

@Component
public class ChapterWrapConverter extends AbstractMapper<Chapter, ChapterWrapDto> {

  private final ChapterDao chapterDao;

  @Autowired
  public ChapterWrapConverter(
      ChapterDao chapterDao
  ) {
    super(Chapter.class, ChapterWrapDto.class);
    this.chapterDao = chapterDao;
  }

  @Override
  public Chapter convertToEntity(ChapterWrapDto dto) {
    return Optional.ofNullable(dto.getId()).flatMap(chapterDao::findById)
        .orElse(null);
  }
}
