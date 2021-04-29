package ru.egorbarinov.questionnaire.mapper.impl.test;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.egorbarinov.questionnaire.dto.test.ChapterDto;
import ru.egorbarinov.questionnaire.dto.test.QuestionDto;
import ru.egorbarinov.questionnaire.entity.Chapter;
import ru.egorbarinov.questionnaire.mapper.impl.AbstractMapper;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ChapterConverter extends AbstractMapper<Chapter, ChapterDto> {

  private final ModelMapper mapper;
  private final QuestionConverter questionConverter;

  @Autowired
  public ChapterConverter(
      ModelMapper mapper,
      QuestionConverter questionConverter) {
    super(Chapter.class, ChapterDto.class);
    this.mapper = mapper;
    this.questionConverter = questionConverter;
  }

  @PostConstruct
  public void setupMapper() {
    mapper.createTypeMap(Chapter.class, ChapterDto.class)
        .addMappings(m -> m.skip(ChapterDto::setTestId))
        .addMappings(m -> m.skip(ChapterDto::setQuestions))
        .setPostConverter(toDtoConverter());
  }

  @Override
  public void mapSpecificFields(Chapter source, ChapterDto destination) {
    destination.setTestId(getTestId(source));
    destination.setQuestions(getQuestionsIsNotDelete(source));
  }

  private UUID getTestId(Chapter source) {
    return Objects.isNull(source) || Objects.isNull(source.getTest()) ? null
        : source.getTest().getId();
  }

  private Set<QuestionDto> getQuestionsIsNotDelete(Chapter source) {
    return Objects.isNull(source) || Objects.isNull(source.getQuestions()) ? null
        : source.getQuestions().stream()
            .filter(q -> !q.getIsDeleted())
            .map(questionConverter::convertToDto)
            .collect(Collectors.toSet());
  }

  @Override
  public Chapter convertToEntity(ChapterDto dto) {
    throw new UnsupportedOperationException("This converter not supported convertToEntity");
  }
}
