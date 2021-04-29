package ru.egorbarinov.questionnaire.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.egorbarinov.questionnaire.dto.admin.UserDetailsDto;
import ru.egorbarinov.questionnaire.service.impl.admin.UserServiceImpl;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserRestController {

  private final UserServiceImpl userService;

  @GetMapping
  @PreAuthorize("hasRole('ADMIN')")
  public List<UserDetailsDto> getUsers() {
    log.info("Get all users");
    return userService.getUsers();
  }

  @GetMapping("/role/{id}")
  public List<UserDetailsDto> getUsersByRoleId(@PathVariable UUID id) {
    log.info("Get all users by roleId {}", id);
    return userService.getUsersByRoleId(id);
  }

  @GetMapping("/{id}")
  public UserDetailsDto getUser(@PathVariable("id") UUID id) {
    log.info("Get user by id {}", id);
    return userService.getUserById(id);
  }

  @PutMapping
  public UserDetailsDto editUser(@RequestBody UserDetailsDto userDetailsDto) {
    log.info("Edit user {}", userDetailsDto);
    return userService.editUser(userDetailsDto);
  }

  @PostMapping("/create")
  public UserDetailsDto createUser(@RequestBody UserDetailsDto userDetailsDto) {
    log.info("Create user {}", userDetailsDto);
    return userService.createUser(userDetailsDto);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable("id") UUID id) {
    log.info("Delete user by id {}", id);
    userService.deleteUserById(id);
  }

}
