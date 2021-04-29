package ru.egorbarinov.questionnaire.mapper.impl.test;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.egorbarinov.questionnaire.dto.test.UserTestDto;
import ru.egorbarinov.questionnaire.entity.UserTest;
import ru.egorbarinov.questionnaire.mapper.impl.AbstractMapper;
import ru.egorbarinov.questionnaire.repository.TestDao;
import ru.egorbarinov.questionnaire.repository.UserDao;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserTestConverter extends AbstractMapper<UserTest, UserTestDto> {

  private final ModelMapper mapper;
  private final TestDao testDao;
  private final UserDao userDao;

  @Autowired
  public UserTestConverter(
      ModelMapper mapper,
      TestDao testDao,
      UserDao userDao
  ) {
    super(UserTest.class, UserTestDto.class);
    this.mapper = mapper;
    this.testDao = testDao;
    this.userDao = userDao;
  }

  @PostConstruct
  public void setupMapper() {
    mapper.createTypeMap(UserTest.class, UserTestDto.class)
        .addMappings(m -> m.skip(UserTestDto::setTestId))
        .addMappings(m -> m.skip(UserTestDto::setUserId))
        .setPostConverter(toDtoConverter());
    mapper.createTypeMap(UserTestDto.class, UserTest.class)
        .addMappings(m -> m.skip(UserTest::setTest))
        .addMappings(m -> m.skip(UserTest::setUser))
        .setPostConverter(toEntityConverter());
  }

  @Override
  public void mapSpecificFields(UserTest source, UserTestDto destination) {
    destination.setTestId(getTestId(source));
    destination.setUserId(getUserId(source));
  }

  private UUID getTestId(UserTest source) {
    return Objects.isNull(source) || Objects.isNull(source.getTest()) ? null
        : source.getTest().getId();
  }

  private UUID getUserId(UserTest source) {
    return Objects.isNull(source) || Objects.isNull(source.getUser()) ? null
        : source.getUser().getId();
  }

  @Override
  public void mapSpecificFields(UserTestDto source, UserTest destination) {
    Optional.ofNullable(source.getTestId()).ifPresent(id ->
        destination.setTest(testDao.findById(id).orElse(null))
    );
    Optional.ofNullable(source.getUserId()).ifPresent(id ->
        destination.setUser(userDao.findById(source.getUserId()).orElse(null))
    );
  }
}
