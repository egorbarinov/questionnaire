package ru.egorbarinov.questionnaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.egorbarinov.questionnaire.entity.Answer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AnswerDao extends JpaRepository<Answer, UUID> {

  Optional<Answer> findByIdAndIsDeletedFalse(UUID id);

  List<Answer> findAllByIsDeletedFalse();
}
