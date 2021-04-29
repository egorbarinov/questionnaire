package ru.egorbarinov.questionnaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.egorbarinov.questionnaire.entity.Chapter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChapterDao extends JpaRepository<Chapter, UUID> {

  Optional<Chapter> findByIdAndIsDeletedFalse(UUID id);

  List<Chapter> findAllByIsDeletedFalse();

}
