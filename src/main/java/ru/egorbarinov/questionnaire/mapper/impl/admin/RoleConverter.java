package ru.egorbarinov.questionnaire.mapper.impl.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.egorbarinov.questionnaire.dto.admin.RoleDto;
import ru.egorbarinov.questionnaire.entity.Role;
import ru.egorbarinov.questionnaire.mapper.impl.AbstractMapper;

@Component
public class RoleConverter extends AbstractMapper<Role, RoleDto> {

  @Autowired
  public RoleConverter() {
    super(Role.class, RoleDto.class);
  }
}
