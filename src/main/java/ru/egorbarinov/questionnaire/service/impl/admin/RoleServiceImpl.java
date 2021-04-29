package ru.egorbarinov.questionnaire.service.impl.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.egorbarinov.questionnaire.dto.admin.RoleDto;
import ru.egorbarinov.questionnaire.mapper.impl.admin.RoleConverter;
import ru.egorbarinov.questionnaire.repository.RoleDao;
import ru.egorbarinov.questionnaire.service.RoleService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

  private final RoleDao roleDao;
  private final RoleConverter roleConverter;

  @Override
  @Transactional(readOnly = true)
  public List<RoleDto> getRoles() {
    return roleDao.findAll().stream()
        .map(roleConverter::convertToDto)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public RoleDto getRoleById(UUID uuid) {
    return roleDao.findById(uuid)
        .map(roleConverter::convertToDto)
        .orElse(null);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<RoleDto> getRoleByName(String name) {
    return roleDao.findByName(name)
            .map(roleConverter::convertToDto);
  }
}
