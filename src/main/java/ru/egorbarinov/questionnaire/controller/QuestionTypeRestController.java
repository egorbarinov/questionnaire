package ru.egorbarinov.questionnaire.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.egorbarinov.questionnaire.dto.common.QuestionTypeDto;
import ru.egorbarinov.questionnaire.service.QuestionTypeService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/type_questions")
public class QuestionTypeRestController {

    private final QuestionTypeService questionTypeDto;

    @GetMapping("/")
    public List<QuestionTypeDto> getTypeQuestions() {
        log.info("Get all type question");
        return questionTypeDto.getQuestionTypes();
    }

    @GetMapping("/{id}")
    public QuestionTypeDto getTypeQuestion(@PathVariable("id") UUID id) {
        log.info("Get question type by id {} ", id);
        return questionTypeDto.getQuestionTypeById(id);
    }

}
