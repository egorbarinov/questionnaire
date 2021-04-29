package ru.egorbarinov.questionnaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.egorbarinov.questionnaire.entity.Role;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleDao extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(String name);
}
