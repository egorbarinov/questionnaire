package ru.egorbarinov.questionnaire.service;

import ru.egorbarinov.questionnaire.dto.manage.QuestionFullDto;
import ru.egorbarinov.questionnaire.dto.test.QuestionDto;

import java.util.List;
import java.util.UUID;

public interface QuestionService {
  List<QuestionDto> getQuestions();

  List<QuestionFullDto> getFullQuestions();

  QuestionDto getQuestionById(UUID uuid);

  QuestionFullDto getQuestionFullById(UUID uuid);

  QuestionFullDto createQuestion(QuestionFullDto questionDto);

  QuestionFullDto editQuestion(QuestionFullDto questionDto);

  void deleteQuestionById(UUID uuid);

}
