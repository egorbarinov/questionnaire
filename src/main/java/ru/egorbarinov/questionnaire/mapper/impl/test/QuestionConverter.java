package ru.egorbarinov.questionnaire.mapper.impl.test;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.egorbarinov.questionnaire.dto.test.AnswerDto;
import ru.egorbarinov.questionnaire.dto.test.QuestionDto;
import ru.egorbarinov.questionnaire.entity.Question;
import ru.egorbarinov.questionnaire.mapper.impl.AbstractMapper;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class QuestionConverter extends AbstractMapper<Question, QuestionDto> {

  private final ModelMapper mapper;
  private final AnswerConverter answerConverter;

  @Autowired
  public QuestionConverter(
      ModelMapper mapper,
      AnswerConverter answerConverter) {
    super(Question.class, QuestionDto.class);
    this.mapper = mapper;
    this.answerConverter = answerConverter;
  }

  @PostConstruct
  public void setupMapper() {
    mapper.createTypeMap(Question.class, QuestionDto.class)
        .addMappings(m -> m.skip(QuestionDto::setQuestionTypeId))
        .addMappings(m -> m.skip(QuestionDto::setAnswers))
        .setPostConverter(toDtoConverter());
  }

  @Override
  public void mapSpecificFields(Question source, QuestionDto destination) {
    destination.setQuestionTypeId(getQuestionTypeId(source));
    destination.setAnswers(getAnswersIsNotDelete(source));
  }

  private UUID getQuestionTypeId(Question source) {
    return Objects.isNull(source) || Objects.isNull(source.getQuestionType()) ? null
        : source.getQuestionType().getId();
  }

  private Set<AnswerDto> getAnswersIsNotDelete(Question source) {
    return Objects.isNull(source) || Objects.isNull(source.getAnswers()) ? null
        : source.getAnswers().stream()
            .filter(q -> !q.getIsDeleted())
            .map(answerConverter::convertToDto)
            .collect(Collectors.toSet());
  }

  @Override
  public Question convertToEntity(QuestionDto dto) {
    throw new UnsupportedOperationException("This converter not supported convertToEntity");
  }
}
