package ru.egorbarinov.questionnaire.service.impl.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.egorbarinov.questionnaire.dto.manage.ChapterWrapDto;
import ru.egorbarinov.questionnaire.dto.manage.QuestionFullDto;
import ru.egorbarinov.questionnaire.dto.test.QuestionDto;
import ru.egorbarinov.questionnaire.entity.Chapter;
import ru.egorbarinov.questionnaire.entity.Question;
import ru.egorbarinov.questionnaire.exception.CustomBadRequest;
import ru.egorbarinov.questionnaire.exception.CustomNotFoundException;
import ru.egorbarinov.questionnaire.mapper.impl.manage.QuestionFullConverter;
import ru.egorbarinov.questionnaire.mapper.impl.test.QuestionConverter;
import ru.egorbarinov.questionnaire.repository.ChapterDao;
import ru.egorbarinov.questionnaire.repository.QuestionDao;
import ru.egorbarinov.questionnaire.service.QuestionService;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

  private final QuestionDao questionDao;
  private final ChapterDao chapterDao;
  private final QuestionConverter questionConverter;
  private final QuestionFullConverter questionFullConverter;

  @Override
  @Transactional(readOnly = true)
  public List<QuestionDto> getQuestions() {
    return questionDao.findAllByIsDeletedFalse().stream()
        .map(questionConverter::convertToDto)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public List<QuestionFullDto> getFullQuestions() {
    return questionDao.findAllByIsDeletedFalse().stream()
        .map(questionFullConverter::convertToDto)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public QuestionDto getQuestionById(UUID uuid) {
    return questionDao.findByIdAndIsDeletedFalse(uuid)
        .map(questionConverter::convertToDto)
        .orElseThrow(CustomNotFoundException::new);
  }

  @Override
  @Transactional(readOnly = true)
  public QuestionFullDto getQuestionFullById(UUID uuid) {
    return questionDao.findByIdAndIsDeletedFalse(uuid)
        .map(questionFullConverter::convertToDto)
        .orElseThrow(CustomNotFoundException::new);
  }

  @Override
  @Transactional
  public QuestionFullDto createQuestion(QuestionFullDto questionDto) {
    if (questionDto.getId() != null) {
      throw new CustomBadRequest();
    }
    Question question = questionDao.save(
        questionFullConverter.convertToEntity(questionDto));

    Set<ChapterWrapDto> questionChapters = Optional
        .ofNullable(questionDto.getChapters())
        .orElseThrow(CustomBadRequest::new);

    questionChapters.stream()
        .map(ChapterWrapDto::getId)
        .filter(Objects::nonNull)
        .map(chapterDao::findByIdAndIsDeletedFalse)
        .map(oChapter -> oChapter.orElse(null))
        .filter(Objects::nonNull)
        .peek(chapter -> chapter.addQuestion(question))
        .forEach(chapterDao::save);

    return getQuestionFullById(question.getId());
  }

  @Override
  @Transactional
  public QuestionFullDto editQuestion(QuestionFullDto questionDto) {
    Optional.ofNullable(questionDto.getId())
        .orElseThrow(CustomBadRequest::new);

    final Set<UUID> activeChaptersIds = questionDao.findByIdAndIsDeletedFalse(questionDto.getId())
        .orElseThrow(CustomNotFoundException::new).getChapters().stream()
        .map(Chapter::getId)
        .collect(Collectors.toSet());

    final Set<UUID> currentChaptersIds;
    if (Objects.isNull(questionDto.getChapters())) {
      currentChaptersIds = new HashSet<>();
    } else {
      currentChaptersIds = questionDto.getChapters().stream()
          .map(ChapterWrapDto::getId)
          .collect(Collectors.toSet());
    }

    Question question = questionDao.save(
        questionFullConverter.convertToEntity(questionDto));

    if (!activeChaptersIds.isEmpty() || !currentChaptersIds.isEmpty()) {

      if (!activeChaptersIds.isEmpty()) {
        activeChaptersIds.stream()
            .filter(chId -> !currentChaptersIds.contains(chId))
            .map(chapterDao::findByIdAndIsDeletedFalse)
            .map(oChapter -> oChapter.orElse(null))
            .filter(Objects::nonNull)
            .peek(ch -> ch.removeQuestion(question))
            .forEach(chapterDao::save);
      }

      if (!currentChaptersIds.isEmpty()) {
        Set<UUID> skipChapters = activeChaptersIds.stream()
            .filter(currentChaptersIds::contains)
            .collect(Collectors.toSet());

        currentChaptersIds.stream()
            .filter(currCh -> !skipChapters.contains(currCh))
            .map(chapterDao::findByIdAndIsDeletedFalse)
            .map(oChapter -> oChapter.orElse(null))
            .filter(Objects::nonNull)
            .peek(chapter -> chapter.addQuestion(question))
            .forEach(chapterDao::save);
      }
    }

    return getQuestionFullById(question.getId());
  }

  @Override
  @Transactional
  public void deleteQuestionById(UUID uuid) {
    Question question = questionDao.findByIdAndIsDeletedFalse(uuid)
        .orElseThrow(CustomNotFoundException::new);

    question.setIsDeleted(true);
    questionDao.save(question);
  }
}
