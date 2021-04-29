package ru.egorbarinov.questionnaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.egorbarinov.questionnaire.entity.QuestionType;

import java.util.UUID;

@Repository
public interface QuestionTypeDao extends JpaRepository<QuestionType, UUID> {
}
