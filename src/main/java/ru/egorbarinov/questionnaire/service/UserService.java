package ru.egorbarinov.questionnaire.service;

import ru.egorbarinov.questionnaire.dto.admin.UserDetailsDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

  List<UserDetailsDto> getUsers();

  UserDetailsDto getUserById(UUID uuid);

  UserDetailsDto createUser(UserDetailsDto userDetailsDto);

  UserDetailsDto editUser(UserDetailsDto userDetailsDto);

  void deleteUserById(UUID uuid);

  List<UserDetailsDto> getUsersByRoleId(UUID roleId);

}
