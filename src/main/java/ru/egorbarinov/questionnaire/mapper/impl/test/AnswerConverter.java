package ru.egorbarinov.questionnaire.mapper.impl.test;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.egorbarinov.questionnaire.dto.test.AnswerDto;
import ru.egorbarinov.questionnaire.entity.Answer;
import ru.egorbarinov.questionnaire.mapper.impl.AbstractMapper;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.UUID;

@Component
public class AnswerConverter extends AbstractMapper<Answer, AnswerDto> {

  private final ModelMapper mapper;

  @Autowired
  public AnswerConverter(
      ModelMapper mapper
  ) {
    super(Answer.class, AnswerDto.class);
    this.mapper = mapper;
  }

  @PostConstruct
  public void setupMapper() {
    mapper.createTypeMap(Answer.class, AnswerDto.class)
        .addMappings(m -> m.skip(AnswerDto::setQuestionId))
        .setPostConverter(toDtoConverter());
  }

  @Override
  public void mapSpecificFields(Answer source, AnswerDto destination) {
    destination.setQuestionId(getQuestionId(source));
  }

  private UUID getQuestionId(Answer source) {
    return Objects.isNull(source) || Objects.isNull(source.getQuestion()) ? null
        : source.getQuestion().getId();
  }

  @Override
  public Answer convertToEntity(AnswerDto dto) {
    throw new UnsupportedOperationException("This converter not supported convertToEntity");
  }
}
