package ru.egorbarinov.questionnaire.service.impl.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.egorbarinov.questionnaire.dto.common.QuestionTypeDto;
import ru.egorbarinov.questionnaire.exception.CustomNotFoundException;
import ru.egorbarinov.questionnaire.mapper.impl.common.QuestionTypeConverter;
import ru.egorbarinov.questionnaire.repository.QuestionTypeDao;
import ru.egorbarinov.questionnaire.service.QuestionTypeService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionTypeServiceImpl implements QuestionTypeService {

  private final QuestionTypeDao questionTypeDao;
  private final QuestionTypeConverter questionTypeConverter;

  @Override
  @Transactional(readOnly = true)
  public List<QuestionTypeDto> getQuestionTypes() {
    return questionTypeDao.findAll().stream()
        .map(questionTypeConverter::convertToDto)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public QuestionTypeDto getQuestionTypeById(UUID uuid) {
    return questionTypeDao.findById(uuid)
        .map(questionTypeConverter::convertToDto)
        .orElseThrow(CustomNotFoundException::new);
  }
}


