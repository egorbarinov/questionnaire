package ru.egorbarinov.questionnaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.egorbarinov.questionnaire.entity.UserTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserTestDao extends JpaRepository<UserTest, UUID> {

  List<UserTest> findAllByUserIdOrderByStartedDesc(UUID userId);

  Optional<UserTest> findFirstByUserIdOrderByStartedDesc(UUID userId);

}
