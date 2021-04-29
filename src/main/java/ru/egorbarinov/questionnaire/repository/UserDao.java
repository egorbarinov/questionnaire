package ru.egorbarinov.questionnaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.egorbarinov.questionnaire.entity.Role;
import ru.egorbarinov.questionnaire.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDao extends JpaRepository<User, UUID> {

  Optional<User> findByIdAndIsDeletedFalse(UUID id);

  List<User> findAllByIsDeletedFalse();

  Optional<User> findByUsernameAndIsDeletedFalse(String username);

  Boolean existsByUsernameAndIsDeletedFalse(String username);

  Boolean existsByEmailAndIsDeletedFalse(String email);

  List<User> findAllByRolesIsContaining(Role role);

}
