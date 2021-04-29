package ru.egorbarinov.questionnaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.egorbarinov.questionnaire.entity.UserTestQuestion;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserTestQuestionDao extends JpaRepository<UserTestQuestion, UUID> {

  List<UserTestQuestion> findAllByUserTestId(UUID userTestId);

}
