package ru.egorbarinov.questionnaire.service;

import ru.egorbarinov.questionnaire.dto.admin.RoleDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleService {

  List<RoleDto> getRoles();

  RoleDto getRoleById(UUID uuid);

  Optional<RoleDto> getRoleByName(String name);
}
