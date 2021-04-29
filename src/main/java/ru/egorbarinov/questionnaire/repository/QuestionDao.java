package ru.egorbarinov.questionnaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.egorbarinov.questionnaire.entity.Question;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuestionDao extends JpaRepository<Question, UUID> {

  Optional<Question> findByIdAndIsDeletedFalse(UUID id);

  List<Question> findAllByIsDeletedFalse();

}
