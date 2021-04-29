package ru.egorbarinov.questionnaire.mapper.impl.test;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.egorbarinov.questionnaire.dto.test.ChapterDto;
import ru.egorbarinov.questionnaire.dto.test.TestDto;
import ru.egorbarinov.questionnaire.entity.Test;
import ru.egorbarinov.questionnaire.mapper.impl.AbstractMapper;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TestConverter extends AbstractMapper<Test, TestDto> {

  private final ModelMapper mapper;
  private final ChapterConverter chapterConverter;

  @Autowired
  public TestConverter(
      ModelMapper mapper,
      ChapterConverter chapterConverter
  ) {
    super(Test.class, TestDto.class);
    this.mapper = mapper;
    this.chapterConverter = chapterConverter;
  }

  @PostConstruct
  public void setupMapper() {
    mapper.createTypeMap(Test.class, TestDto.class)
        .addMappings(m -> m.skip(TestDto::setAuthorId))
        .addMappings(m -> m.skip(TestDto::setChapters))
        .setPostConverter(toDtoConverter());
  }

  @Override
  public void mapSpecificFields(Test source, TestDto destination) {
    destination.setAuthorId(getAuthorId(source));
    destination.setChapters(getChaptersIsNotDelete(source));
  }

  private UUID getAuthorId(Test source) {
    return Objects.isNull(source) || Objects.isNull(source.getAuthor()) ? null
        : source.getAuthor().getId();
  }

  private Set<ChapterDto> getChaptersIsNotDelete(Test source) {
    return Objects.isNull(source) || Objects.isNull(source.getChapters()) ? null
        : source.getChapters().stream()
            .filter(ch -> !ch.getIsDeleted())
            .map(chapterConverter::convertToDto)
            .collect(Collectors.toSet());
  }

  @Override
  public Test convertToEntity(TestDto dto) {
    throw new UnsupportedOperationException("This converter not supported convertToEntity");
  }
}
