package ru.egorbarinov.questionnaire.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.egorbarinov.questionnaire.dto.manage.QuestionFullDto;
import ru.egorbarinov.questionnaire.dto.test.QuestionDto;
import ru.egorbarinov.questionnaire.service.impl.common.QuestionServiceImpl;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/questions")
public class QuestionRestController {

    private final QuestionServiceImpl questionService;

    @GetMapping("/")
    public List<QuestionDto> getQuestions() {
        log.info("Get all question");
        return questionService.getQuestions();
    }

    @GetMapping("/{id}")
    public QuestionDto getQuestion(@PathVariable("id") UUID id) {
        log.info("Get question by id {}", id);
        return questionService.getQuestionById(id);
    }

    @GetMapping("/full")
    public List<QuestionFullDto> getFullQuestions() {
        log.info("Get all full question");
        return questionService.getFullQuestions();
    }

    @GetMapping("/full/{id}")
    public QuestionFullDto getFullQuestion(@PathVariable("id") UUID id) {
        log.info("Get full question by id {}", id);
        return questionService.getQuestionFullById(id);
    }

    @PutMapping("/")
    public QuestionFullDto editQuestion(@RequestBody QuestionFullDto questionDto) {
        log.info("Edit question {}", questionDto);
        return questionService.editQuestion(questionDto);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable("id") UUID id) {
        log.info("Delete question by id {}", id);
        questionService.deleteQuestionById(id);
    }

    @PostMapping("/create")
    public QuestionFullDto createQuestion(@RequestBody QuestionFullDto questionDto) {
        log.info("Create question {}", questionDto);
        return questionService.createQuestion(questionDto);
    }

}
