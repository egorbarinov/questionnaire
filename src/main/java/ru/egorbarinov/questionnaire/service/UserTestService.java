package ru.egorbarinov.questionnaire.service;

import ru.egorbarinov.questionnaire.dto.test.QuestionDto;
import ru.egorbarinov.questionnaire.dto.test.UserTestDto;
import ru.egorbarinov.questionnaire.dto.test.UserTestQuestionDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserTestService {

  List<UserTestDto> getUserTests(UUID userId);

  UserTestDto getActiveUserTest(UUID userId);

  UserTestDto startUserTest(UUID testId, UUID userId);

  Optional<QuestionDto> createUserAnswer(UserTestQuestionDto userTestQuestionDto, UUID userId);

  Optional<QuestionDto> getNextQuestion(UUID userTestId);

  List<UserTestQuestionDto> getFailQuestionsByUserTestId(UUID userTestId);

  UserTestDto getUserTestById(UUID userTestId);

}
