package ru.egorbarinov.questionnaire.service.impl.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.egorbarinov.questionnaire.dto.manage.TestFullDto;
import ru.egorbarinov.questionnaire.dto.test.TestDto;
import ru.egorbarinov.questionnaire.entity.Test;
import ru.egorbarinov.questionnaire.exception.CustomBadRequest;
import ru.egorbarinov.questionnaire.exception.CustomNotFoundException;
import ru.egorbarinov.questionnaire.mapper.impl.manage.TestFullConverter;
import ru.egorbarinov.questionnaire.mapper.impl.test.TestConverter;
import ru.egorbarinov.questionnaire.repository.TestDao;
import ru.egorbarinov.questionnaire.service.TestService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

  private final TestDao testDao;

  private final TestConverter testConverter;
  private final TestFullConverter testFullConverter;

  @Override
  @Transactional(readOnly = true)
  public List<TestDto> getTests() {
    return testDao.findAllByIsDeletedFalse().stream()
        .map(testConverter::convertToDto)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public List<TestFullDto> getFullTests() {
    return testDao.findAll().stream()
        .map(testFullConverter::convertToDto)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public TestDto getTestById(UUID uuid) {
    return testDao.findByIdAndIsDeletedFalse(uuid)
        .map(testConverter::convertToDto)
        .orElseThrow(CustomNotFoundException::new);
  }

  @Override
  @Transactional(readOnly = true)
  public TestFullDto getTestFullById(UUID uuid) {
    return testDao.findById(uuid)
        .map(testFullConverter::convertToDto)
        .orElseThrow(CustomNotFoundException::new);
  }

  @Override
  @Transactional
  public TestFullDto createTest(TestFullDto testFullDto, UUID userId) {
    if (userId == null && testFullDto.getId() != null) {
      throw new CustomBadRequest();
    }
    testFullDto.setCreated(LocalDateTime.now());
    testFullDto.setAuthorId(userId);
    return testFullConverter.convertToDto(
        testDao.save(
            testFullConverter.convertToEntity(testFullDto)));
  }

  @Override
  @Transactional
  public TestFullDto editTest(TestFullDto questionDto) {
    Optional.ofNullable(questionDto.getId())
        .orElseThrow(CustomBadRequest::new);
    return testFullConverter.convertToDto(
        testDao.save(
            testFullConverter.convertToEntity(questionDto)));
  }

  @Override
  @Transactional
  public List<TestDto> getTestsByAuthorId(UUID authorId) {
    return testDao.findAllByAuthorId(authorId).stream()
        .map(testConverter::convertToDto)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public void deleteTestById(UUID uuid) {
    Test test = testDao.findByIdAndIsDeletedFalse(uuid)
        .orElseThrow(CustomNotFoundException::new);

    test.setIsDeleted(true);
    testDao.save(test);
  }

}
