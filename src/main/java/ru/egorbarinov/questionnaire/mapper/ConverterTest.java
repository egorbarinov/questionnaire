package ru.egorbarinov.questionnaire.mapper;

public interface ConverterTest<E, D> {

  D convertToDto(E entity);

  E convertToEntity(D dto);
}
