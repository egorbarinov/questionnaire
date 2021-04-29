package ru.egorbarinov.questionnaire.service.impl.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.egorbarinov.questionnaire.dto.manage.ChapterFullDto;
import ru.egorbarinov.questionnaire.dto.test.ChapterDto;
import ru.egorbarinov.questionnaire.entity.Chapter;
import ru.egorbarinov.questionnaire.exception.CustomBadRequest;
import ru.egorbarinov.questionnaire.exception.CustomNotFoundException;
import ru.egorbarinov.questionnaire.mapper.impl.manage.ChapterFullConverter;
import ru.egorbarinov.questionnaire.mapper.impl.test.ChapterConverter;
import ru.egorbarinov.questionnaire.repository.ChapterDao;
import ru.egorbarinov.questionnaire.service.ChapterService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChapterServiceImpl implements ChapterService {

  private final ChapterDao chapterDao;
  private final ChapterConverter chapterConverter;
  private final ChapterFullConverter chapterFullConverter;

  @Override
  @Transactional(readOnly = true)
  public List<ChapterDto> getChapters() {
    return chapterDao.findAll().stream()
        .map(chapterConverter::convertToDto)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public List<ChapterFullDto> getFullChapters() {
    return chapterDao.findAll().stream()
        .map(chapterFullConverter::convertToDto)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public ChapterDto getChapterById(UUID uuid) {
    return chapterDao.findByIdAndIsDeletedFalse(uuid)
        .map(chapterConverter::convertToDto)
        .orElseThrow(CustomNotFoundException::new);
  }

  @Override
  @Transactional(readOnly = true)
  public ChapterFullDto getChapterFullById(UUID uuid) {
    return chapterDao.findById(uuid)
        .map(chapterFullConverter::convertToDto)
        .orElseThrow(CustomNotFoundException::new);
  }

  @Override
  @Transactional
  public ChapterFullDto createChapter(ChapterFullDto chapterDto) {
    if (chapterDto.getId() != null) {
      throw new CustomBadRequest();
    }
    return chapterFullConverter.convertToDto(
        chapterDao.save(
            chapterFullConverter.convertToEntity(chapterDto)));
  }

  @Override
  @Transactional
  public ChapterFullDto editChapter(ChapterFullDto chapterDto) {
    Optional.ofNullable(chapterDto.getId())
        .orElseThrow(CustomBadRequest::new);
    return chapterFullConverter.convertToDto(
        chapterDao.save(
            chapterFullConverter.convertToEntity(chapterDto)));
  }

  @Override
  @Transactional
  public void deleteChapterById(UUID uuid) {
    Chapter chapter = chapterDao.findByIdAndIsDeletedFalse(uuid)
        .orElseThrow(CustomNotFoundException::new);

    chapter.setIsDeleted(true);
    chapterDao.save(chapter);
  }
}



