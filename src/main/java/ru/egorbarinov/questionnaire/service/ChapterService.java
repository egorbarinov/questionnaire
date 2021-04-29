package ru.egorbarinov.questionnaire.service;

import ru.egorbarinov.questionnaire.dto.manage.ChapterFullDto;
import ru.egorbarinov.questionnaire.dto.test.ChapterDto;

import java.util.List;
import java.util.UUID;

public interface ChapterService {

  List<ChapterDto> getChapters();

  List<ChapterFullDto> getFullChapters();

  ChapterDto getChapterById(UUID uuid);

  ChapterFullDto getChapterFullById(UUID uuid);

  ChapterFullDto createChapter(ChapterFullDto topicDto);

  ChapterFullDto editChapter(ChapterFullDto topicDto);

  void deleteChapterById(UUID uuid);
}
