package ru.egorbarinov.questionnaire.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.egorbarinov.questionnaire.dto.manage.AnswerFullDto;
import ru.egorbarinov.questionnaire.dto.test.AnswerDto;
import ru.egorbarinov.questionnaire.service.impl.common.AnswerServiceImpl;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/answers")
public class AnswerRestController {

    private final AnswerServiceImpl answerService;

    @PostMapping("/create")
    public AnswerFullDto createAnswer(@RequestBody AnswerFullDto answerDto) {
        log.info("Create answer {}", answerDto);
        return answerService.createAnswer(answerDto);
    }

    @PutMapping("/")
    public AnswerFullDto editAnswer(@RequestBody AnswerFullDto answerDto) {
        log.info("Edit answer {}", answerDto);
        return answerService.editAnswer(answerDto);
    }

    @GetMapping("/full")
    public List<AnswerFullDto> getListFullAnswers() {
        log.info("Get list answer");
        return answerService.getAnswersFull();
    }

    @GetMapping("/full/{id}")
    public AnswerFullDto getAnswerFull(@PathVariable UUID id) {
        log.info("Get full answer by id {}", id);
        return answerService.getAnswerFullById(id);
    }

    @GetMapping("/")
    public List<AnswerDto> getListAnswers() {
        log.info("Get full list answer");
        return answerService.getAnswers();
    }

    @GetMapping("/{id}")
    public AnswerDto getAnswer(@PathVariable UUID id) {
        log.info("Get answer by id {}", id);
        return answerService.getAnswerById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAnswer(@PathVariable("id") UUID id) {
        log.info("Delete answer by id {}", id);
        answerService.deleteAnswerById(id);
    }
}
