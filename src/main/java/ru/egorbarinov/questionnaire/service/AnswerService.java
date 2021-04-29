package ru.egorbarinov.questionnaire.service;

import ru.egorbarinov.questionnaire.dto.manage.AnswerFullDto;
import ru.egorbarinov.questionnaire.dto.test.AnswerDto;

import java.util.List;
import java.util.UUID;

public interface AnswerService {

  List<AnswerDto> getAnswers();

  AnswerDto getAnswerById(UUID uuid);

  List<AnswerFullDto> getAnswersFull();

  AnswerFullDto getAnswerFullById(UUID uuid);

  AnswerFullDto createAnswer(AnswerFullDto answerDto);

  AnswerFullDto editAnswer(AnswerFullDto answerDto);

  void deleteAnswerById(UUID uuid);

}
