package ru.egorbarinov.questionnaire.service.impl.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.egorbarinov.questionnaire.dto.manage.AnswerFullDto;
import ru.egorbarinov.questionnaire.dto.test.AnswerDto;
import ru.egorbarinov.questionnaire.entity.Answer;
import ru.egorbarinov.questionnaire.exception.CustomBadRequest;
import ru.egorbarinov.questionnaire.exception.CustomNotFoundException;
import ru.egorbarinov.questionnaire.mapper.impl.manage.AnswerFullConverter;
import ru.egorbarinov.questionnaire.mapper.impl.test.AnswerConverter;
import ru.egorbarinov.questionnaire.repository.AnswerDao;
import ru.egorbarinov.questionnaire.service.AnswerService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

  private final AnswerDao answerDao;
  private final AnswerConverter answerConverter;
  private final AnswerFullConverter answerFullConverter;

  @Override
  @Transactional(readOnly = true)
  public List<AnswerDto> getAnswers() {
    return answerDao.findAllByIsDeletedFalse().stream()
        .map(answerConverter::convertToDto)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public AnswerDto getAnswerById(UUID uuid) {
    return answerDao.findByIdAndIsDeletedFalse(uuid)
        .map(answerConverter::convertToDto)
        .orElseThrow(CustomNotFoundException::new);
  }

  @Override
  @Transactional(readOnly = true)
  public List<AnswerFullDto> getAnswersFull() {
    return answerDao.findAllByIsDeletedFalse().stream()
        .map(answerFullConverter::convertToDto)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public AnswerFullDto getAnswerFullById(UUID uuid) {
    return answerDao.findByIdAndIsDeletedFalse(uuid)
        .map(answerFullConverter::convertToDto)
        .orElseThrow(CustomNotFoundException::new);
  }

  @Override
  @Transactional
  public AnswerFullDto createAnswer(AnswerFullDto answerDto) {
    if (answerDto.getId() != null) {
      throw new CustomBadRequest();
    }
    return answerFullConverter.convertToDto(
        answerDao.save(
            answerFullConverter.convertToEntity(answerDto)));
  }

  @Override
  @Transactional
  public AnswerFullDto editAnswer(AnswerFullDto answerDto) {
    Optional.ofNullable(answerDto.getId())
        .orElseThrow(CustomBadRequest::new);
    return answerFullConverter.convertToDto(
        answerDao.save(
            answerFullConverter.convertToEntity(answerDto)));
  }

  @Override
  @Transactional
  public void deleteAnswerById(UUID uuid) {
    Answer answer = answerDao.findByIdAndIsDeletedFalse(uuid)
        .orElseThrow(CustomNotFoundException::new);

    answer.setIsDeleted(true);
    answerDao.save(answer);
  }
}
