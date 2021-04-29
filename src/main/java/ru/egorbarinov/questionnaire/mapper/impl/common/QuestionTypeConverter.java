package ru.egorbarinov.questionnaire.mapper.impl.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.egorbarinov.questionnaire.dto.common.QuestionTypeDto;
import ru.egorbarinov.questionnaire.entity.QuestionType;
import ru.egorbarinov.questionnaire.mapper.impl.AbstractMapper;

@Component
public class QuestionTypeConverter extends AbstractMapper<QuestionType, QuestionTypeDto> {

  @Autowired
  public QuestionTypeConverter() {
    super(QuestionType.class, QuestionTypeDto.class);
  }
}
