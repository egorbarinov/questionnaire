package ru.egorbarinov.questionnaire.mapper.impl.manage;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.egorbarinov.questionnaire.dto.manage.TestFullDto;
import ru.egorbarinov.questionnaire.entity.Test;
import ru.egorbarinov.questionnaire.mapper.impl.AbstractMapper;
import ru.egorbarinov.questionnaire.repository.UserDao;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Component
public class TestFullConverter extends AbstractMapper<Test, TestFullDto> {

  private final ModelMapper mapper;
  private final UserDao userDao;

  @Autowired
  public TestFullConverter(
      ModelMapper mapper,
      UserDao userDao
  ) {
    super(Test.class, TestFullDto.class);
    this.mapper = mapper;
    this.userDao = userDao;
  }

  @PostConstruct
  public void setupMapper() {
    mapper.createTypeMap(Test.class, TestFullDto.class)
        .addMappings(m -> m.skip(TestFullDto::setAuthorId))
        .setPostConverter(toDtoConverter());
    mapper.createTypeMap(TestFullDto.class, Test.class)
        .addMappings(m -> m.skip(Test::setAuthor))
//        .addMappings(m -> m.skip(Test::setCreated))
        .setPostConverter(toEntityConverter());
  }

  @Override
  public void mapSpecificFields(Test source, TestFullDto destination) {
    destination.setAuthorId(getAuthorId(source));
  }

  private UUID getAuthorId(Test source) {
    return Objects.isNull(source) || Objects.isNull(source.getAuthor()) ? null
        : source.getAuthor().getId();
  }

  @Override
  public void mapSpecificFields(TestFullDto source, Test destination) {
    Optional.ofNullable(source.getAuthorId()).ifPresent(id ->
        destination.setAuthor(userDao.findById(id).orElse(null))
    );

  }
}
