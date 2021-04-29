package ru.egorbarinov.questionnaire.mapper.impl.admin;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.egorbarinov.questionnaire.dto.admin.UserDetailsDto;
import ru.egorbarinov.questionnaire.entity.User;
import ru.egorbarinov.questionnaire.mapper.impl.AbstractMapper;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
public class UserConverter extends AbstractMapper<User, UserDetailsDto> {

  private final ModelMapper mapper;

  @Autowired
  public UserConverter(
      ModelMapper mapper
  ) {
    super(User.class, UserDetailsDto.class);
    this.mapper = mapper;
  }

  @PostConstruct
  public void setupMapper() {
    mapper.createTypeMap(User.class, UserDetailsDto.class)
        .addMappings(m -> m.skip(UserDetailsDto::setPassword))
        .addMappings(m -> m.skip(UserDetailsDto::setRepeatPassword))
        .setPostConverter(toDtoConverter());
    mapper.createTypeMap(UserDetailsDto.class, User.class)
        .addMappings(m -> m.skip(User::setPassword))
        .setPostConverter(toEntityConverter());
  }

  @Override
  public void mapSpecificFields(User source, UserDetailsDto destination) {
    destination.setPassword(source.getPassword());
    destination.setRepeatPassword(null);
  }

  @Override
  public void mapSpecificFields(UserDetailsDto source, User destination) {
    Optional.ofNullable(source.getPassword())
        .ifPresent(destination::setPassword);
  }

}
