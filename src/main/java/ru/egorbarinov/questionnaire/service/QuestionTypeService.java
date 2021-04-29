package ru.egorbarinov.questionnaire.service;

import ru.egorbarinov.questionnaire.dto.common.QuestionTypeDto;

import java.util.List;
import java.util.UUID;

public interface QuestionTypeService {

  List<QuestionTypeDto> getQuestionTypes();

  QuestionTypeDto getQuestionTypeById(UUID uuid);
}
