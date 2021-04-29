package ru.egorbarinov.questionnaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.egorbarinov.questionnaire.entity.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TestDao extends JpaRepository<Test, UUID> {

  Optional<Test> findByIdAndIsDeletedFalse(UUID id);

  List<Test> findAllByIsDeletedFalse();

  List<Test> findAllByAuthorId(UUID userId);

}
