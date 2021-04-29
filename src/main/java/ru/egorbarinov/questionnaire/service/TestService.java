package ru.egorbarinov.questionnaire.service;

import ru.egorbarinov.questionnaire.dto.manage.TestFullDto;
import ru.egorbarinov.questionnaire.dto.test.TestDto;

import java.util.List;
import java.util.UUID;

public interface TestService {

  List<TestDto> getTests();

  List<TestFullDto> getFullTests();

  TestDto getTestById(UUID uuid);

  TestFullDto getTestFullById(UUID uuid);

  void deleteTestById(UUID uuid);

  TestFullDto createTest(TestFullDto questionDto, UUID userId);

  TestFullDto editTest(TestFullDto questionDto);

  List<TestDto> getTestsByAuthorId(UUID authorId);

}
