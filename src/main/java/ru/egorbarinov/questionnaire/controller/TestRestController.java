package ru.egorbarinov.questionnaire.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.egorbarinov.questionnaire.dto.admin.UserDetailsDto;
import ru.egorbarinov.questionnaire.dto.manage.TestFullDto;
import ru.egorbarinov.questionnaire.dto.test.TestDto;
import ru.egorbarinov.questionnaire.dto.test.UserTestDto;
import ru.egorbarinov.questionnaire.service.UserTestService;
import ru.egorbarinov.questionnaire.service.impl.common.TestServiceImpl;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tests")
public class TestRestController {

    private final TestServiceImpl testService;
    private final UserTestService userTestService;

    @GetMapping
    public List<TestDto> getTests() {
        log.info("Get all tests");
        return testService.getTests();
    }

    @GetMapping("/{id}")
    public TestDto getTest(@PathVariable("id") UUID id) {
        log.info("Get test by id {}", id);
        return testService.getTestById(id);
    }

    @PutMapping
    public TestFullDto editTest(@RequestBody TestFullDto testDto) {
        log.info("Edit test {}", testDto);
        return testService.editTest(testDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTest(@PathVariable("id") UUID id) {
        log.info("Delete test by id {}", id);
        testService.deleteTestById(id);
    }

    @PostMapping("/create")
    public TestFullDto createTest(@RequestBody TestFullDto testDto, Authentication authentication) {
        log.info("Create test {}", testDto);
        UserDetailsDto currentUser = (UserDetailsDto) authentication.getPrincipal();
        return testService.createTest(testDto, currentUser.getId());
    }

    @GetMapping("/user/{id}")
    public List<TestDto> getTestsByUserId(@PathVariable("id") UUID id) {
        log.info("Get all test for user id {}", id);
        return testService.getTestsByAuthorId(id);
    }

    @GetMapping("/user")
    public List<TestDto> getAllTestCurrentUser(Authentication authentication) {
        UserDetailsDto currentUser = (UserDetailsDto) authentication.getPrincipal();
        log.info("Get all test for user {}", currentUser.getId());
        return testService.getTestsByAuthorId(currentUser.getId());
    }

    @GetMapping("/user-test/{id}")
    public List<UserTestDto> getUserTestById(@PathVariable("id") UUID id) {
        log.info("Get all user test for user id {}", id);
        return userTestService.getUserTests(id);
    }

    @GetMapping("/user-test")
    public List<UserTestDto> getAllUserTestCurrentUser(Authentication authentication) {
        UserDetailsDto currentUser = (UserDetailsDto) authentication.getPrincipal();
        log.info("Get all user test for user {}", currentUser.getId());
        return userTestService.getUserTests(currentUser.getId());
    }

}
