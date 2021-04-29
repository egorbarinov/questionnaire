package ru.egorbarinov.questionnaire.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.egorbarinov.questionnaire.dto.manage.ChapterFullDto;
import ru.egorbarinov.questionnaire.dto.test.ChapterDto;
import ru.egorbarinov.questionnaire.service.impl.common.ChapterServiceImpl;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/chapters")
public class ChapterRestController {

    private final ChapterServiceImpl chapterService;

    @GetMapping
    public List<ChapterDto> getChapters() {
        log.info("Get all chapters");
        return chapterService.getChapters();
    }

    @GetMapping("/{id}")
    public ChapterDto getChapter(@PathVariable("id") UUID id) {
        log.info("Get chapter by id {}", id);
        return chapterService.getChapterById(id);
    }

    @PutMapping
    public ChapterFullDto editChapter(@RequestBody ChapterFullDto chapterDto) {
        log.info("Edit chapter {}", chapterDto);
        return chapterService.editChapter(chapterDto);
    }

    @DeleteMapping("/{id}")
    public void deleteChapter(@PathVariable("id") UUID id) {
        log.info("Delete chapter by id {}", id);
        chapterService.deleteChapterById(id);
    }

    @PostMapping("/create")
    public ChapterFullDto createChapter(@RequestBody ChapterFullDto chapterDto) {
        log.info("Create chapter {}", chapterDto);
        return chapterService.createChapter(chapterDto);
    }
}
