package ru.egorbarinov.questionnaire.service.impl.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.egorbarinov.questionnaire.dto.admin.UserDetailsDto;
import ru.egorbarinov.questionnaire.entity.Role;
import ru.egorbarinov.questionnaire.entity.User;
import ru.egorbarinov.questionnaire.exception.CustomBadRequest;
import ru.egorbarinov.questionnaire.exception.CustomNotFoundException;
import ru.egorbarinov.questionnaire.mapper.impl.admin.UserConverter;
import ru.egorbarinov.questionnaire.repository.RoleDao;
import ru.egorbarinov.questionnaire.repository.UserDao;
import ru.egorbarinov.questionnaire.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

  private final UserDao userDao;
  private final RoleDao roleDao;
  private final UserConverter userConverter;
  private final PasswordEncoder encoder;

  @Override
  @Transactional(readOnly = true)
  public List<UserDetailsDto> getUsers() {
    return userDao.findAllByIsDeletedFalse().stream()
        .map(userConverter::convertToDto)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public UserDetailsDto getUserById(UUID uuid) {
    return userDao.findByIdAndIsDeletedFalse(uuid)
        .map(userConverter::convertToDto)
        .orElseThrow(CustomNotFoundException::new);
  }

  @Override
  @Transactional
  public UserDetailsDto createUser(UserDetailsDto userDetailsDto) {
    if (userDetailsDto.getId() != null) {
      throw new CustomBadRequest();
    }
    Optional.ofNullable(userDetailsDto.getPassword())
        .orElseThrow(CustomBadRequest::new);
    userDetailsDto.setPassword(encoder.encode(userDetailsDto.getPassword()));
    return userConverter.convertToDto(
        userDao.save(
            userConverter.convertToEntity(userDetailsDto)));
  }

  @Override
  @Transactional
  public UserDetailsDto editUser(UserDetailsDto userDetailsDto) {
    Optional.ofNullable(userDetailsDto.getId())
        .orElseThrow(CustomBadRequest::new);
    User user = userDao.findByIdAndIsDeletedFalse(userDetailsDto.getId())
        .orElseThrow(CustomNotFoundException::new);
    userDetailsDto.setPassword(user.getPassword());
    return userConverter.convertToDto(
        userDao.save(
            userConverter.convertToEntity(userDetailsDto)));
  }

  @Override
  @Transactional
  public void deleteUserById(UUID uuid) {
    User user = userDao.findByIdAndIsDeletedFalse(uuid)
        .orElseThrow(CustomNotFoundException::new);

    user.setIsDeleted(true);
    userDao.save(user);
  }

  @Override
  @Transactional(readOnly = true)
  public List<UserDetailsDto> getUsersByRoleId(UUID roleId) {
    Role role = roleDao.findById(roleId)
        .orElseThrow(CustomNotFoundException::new);

    return userDao.findAllByRolesIsContaining(role).stream()
        .map(userConverter::convertToDto)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userDao.findByUsernameAndIsDeletedFalse(username)
        .orElseThrow(
            () -> new UsernameNotFoundException("User Not Found with username: " + username));
    return userConverter.convertToDto(user);
  }

}
