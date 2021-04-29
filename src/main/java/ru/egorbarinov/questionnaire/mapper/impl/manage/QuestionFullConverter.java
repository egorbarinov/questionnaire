package ru.egorbarinov.questionnaire.mapper.impl.manage;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.egorbarinov.questionnaire.dto.manage.QuestionFullDto;
import ru.egorbarinov.questionnaire.entity.Question;
import ru.egorbarinov.questionnaire.mapper.impl.AbstractMapper;
import ru.egorbarinov.questionnaire.repository.QuestionTypeDao;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Component
public class QuestionFullConverter extends AbstractMapper<Question, QuestionFullDto> {

  private final ModelMapper mapper;
  private final QuestionTypeDao questionTypeDao;

  @Autowired
  public QuestionFullConverter(
      ModelMapper mapper,
      QuestionTypeDao questionTypeDao
  ) {
    super(Question.class, QuestionFullDto.class);
    this.mapper = mapper;
    this.questionTypeDao = questionTypeDao;
  }

  @PostConstruct
  public void setupMapper() {
    mapper.createTypeMap(Question.class, QuestionFullDto.class)
        .addMappings(m -> m.skip(QuestionFullDto::setQuestionTypeId))
        .setPostConverter(toDtoConverter());
    mapper.createTypeMap(QuestionFullDto.class, Question.class)
        .addMappings(m -> m.skip(Question::setQuestionType))
        .setPostConverter(toEntityConverter());
  }

  @Override
  public void mapSpecificFields(Question source, QuestionFullDto destination) {
    destination.setQuestionTypeId(getQuestionTypeId(source));
  }

  private UUID getQuestionTypeId(Question source) {
    return Objects.isNull(source) || Objects.isNull(source.getQuestionType()) ? null
        : source.getQuestionType().getId();
  }

  @Override
  public void mapSpecificFields(QuestionFullDto source, Question destination) {
    Optional.ofNullable(source.getQuestionTypeId()).ifPresent(id ->
        destination.setQuestionType(questionTypeDao.findById(id).orElse(null))
    );
  }
}
