package ru.egorbarinov.questionnaire.mapper.impl.test;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.egorbarinov.questionnaire.dto.test.UserTestQuestionDto;
import ru.egorbarinov.questionnaire.entity.UserTestQuestion;
import ru.egorbarinov.questionnaire.mapper.impl.AbstractMapper;
import ru.egorbarinov.questionnaire.repository.QuestionDao;
import ru.egorbarinov.questionnaire.repository.UserTestDao;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserTestQuestionConverter extends
        AbstractMapper<UserTestQuestion, UserTestQuestionDto> {

  private final ModelMapper mapper;
  private final UserTestDao userTestDao;
  private final QuestionDao questionDao;

  @Autowired
  public UserTestQuestionConverter(
      ModelMapper mapper,
      UserTestDao userTestDao,
      QuestionDao questionDao
  ) {
    super(UserTestQuestion.class, UserTestQuestionDto.class);
    this.mapper = mapper;
    this.userTestDao = userTestDao;
    this.questionDao = questionDao;
  }

  @PostConstruct
  public void setupMapper() {
    mapper.createTypeMap(UserTestQuestion.class, UserTestQuestionDto.class)
        .addMappings(m -> m.skip(UserTestQuestionDto::setUserTestId))
        .addMappings(m -> m.skip(UserTestQuestionDto::setQuestionId))
        .setPostConverter(toDtoConverter());
    mapper.createTypeMap(UserTestQuestionDto.class, UserTestQuestion.class)
        .addMappings(m -> m.skip(UserTestQuestion::setUserTest))
        .addMappings(m -> m.skip(UserTestQuestion::setQuestion))
        .setPostConverter(toEntityConverter());
  }

  @Override
  public void mapSpecificFields(UserTestQuestion source, UserTestQuestionDto destination) {
    destination.setUserTestId(getUserTestId(source));
    destination.setQuestionId(getQuestionId(source));
  }

  private UUID getUserTestId(UserTestQuestion source) {
    return Objects.isNull(source) || Objects.isNull(source.getUserTest()) ? null
        : source.getUserTest().getId();
  }

  private UUID getQuestionId(UserTestQuestion source) {
    return Objects.isNull(source) || Objects.isNull(source.getQuestion()) ? null
        : source.getQuestion().getId();
  }

  @Override
  public void mapSpecificFields(UserTestQuestionDto source, UserTestQuestion destination) {
    Optional.ofNullable(source.getUserTestId()).ifPresent(id ->
        destination.setUserTest(userTestDao.findById(id).orElse(null))
    );
    Optional.ofNullable(source.getQuestionId()).ifPresent(id ->
        destination.setQuestion(questionDao.findById(source.getQuestionId()).orElse(null))
    );
  }
}
