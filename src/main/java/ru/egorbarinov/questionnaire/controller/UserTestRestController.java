package ru.egorbarinov.questionnaire.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.egorbarinov.questionnaire.dto.admin.UserDetailsDto;
import ru.egorbarinov.questionnaire.dto.test.QuestionDto;
import ru.egorbarinov.questionnaire.dto.test.UserTestDto;
import ru.egorbarinov.questionnaire.dto.test.UserTestQuestionDto;
import ru.egorbarinov.questionnaire.exception.CustomNotFoundException;
import ru.egorbarinov.questionnaire.service.UserTestService;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user-test")
public class UserTestRestController {

  private final UserTestService userTestService;
  @GetMapping
  public List<UserTestDto> getUserTests(Authentication authentication) {
    if (authentication == null) {
      return Collections.emptyList();
    }
    UserDetailsDto currentUser = (UserDetailsDto) authentication.getPrincipal();
    log.info("Get all user tests by userId {}", currentUser.getId());
    return userTestService.getUserTests(currentUser.getId());

  }

  @GetMapping("/active")
  public UserTestDto getActiveUserTest(Authentication authentication) {
    UserDetailsDto currentUser = (UserDetailsDto) authentication.getPrincipal();
    log.info("Get active user test by userId {}", currentUser.getId());
    return userTestService.getActiveUserTest(currentUser.getId());
  }

  @GetMapping("/test/{id}")
  public UserTestDto startUserTest(@PathVariable("id") UUID testId,
      Authentication authentication) {
    if (authentication == null) {
      return userTestService.startUserTest(testId, getUuid(null));
    }
    UserDetailsDto currentUser = (UserDetailsDto) authentication.getPrincipal();
    log.info("Start user test by testId {} and userId {}", testId, currentUser.getId());
    return userTestService.startUserTest(testId, currentUser.getId());
  }

  @PostMapping("/create-answer")
  public QuestionDto createUserAnswer(@RequestBody UserTestQuestionDto userTestQuestionDto,
                                      Authentication authentication) {
    if (authentication == null) {
      return userTestService.createUserAnswer(userTestQuestionDto, UUID.randomUUID())
              .orElseThrow(CustomNotFoundException::new);
    }
    UserDetailsDto currentUser = (UserDetailsDto) authentication.getPrincipal();
    log.info("Create user answer {} by userId {}", userTestQuestionDto, currentUser.getId());
    return userTestService.createUserAnswer(userTestQuestionDto, currentUser.getId())
        .orElseThrow(CustomNotFoundException::new);
  }

  private UUID getUuid(Authentication authentication) {
    if (authentication == null) {
      return UUID.randomUUID();
    } else {
      UserDetailsDto currentUser = (UserDetailsDto) authentication.getPrincipal();
       return  currentUser.getId();
    }
  }

  @GetMapping("/test/{id}/next-question")
  public QuestionDto getNextQuestion(@PathVariable("id") UUID userTestId) {
    log.info("Next question by userTestId {}", userTestId);
    return userTestService.getNextQuestion(userTestId)
        .orElseThrow(CustomNotFoundException::new);
  }

  @GetMapping("/{id}/fails")
  public List<UserTestQuestionDto> getFailQuestionsByUserTestId(@PathVariable("id") UUID userTestId) {
    log.info("Get fails question by userTestId {}", userTestId);
    return userTestService.getFailQuestionsByUserTestId(userTestId);
  }

  @GetMapping("/{id}")
  public UserTestDto getUserTest(@PathVariable("id") UUID userTestId) {
    log.info("Get user test by id {}", userTestId);
    return userTestService.getUserTestById(userTestId);
  }

}
