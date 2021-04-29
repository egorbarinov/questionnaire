package ru.egorbarinov.questionnaire.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomNotFoundException extends RuntimeException {

    private ErrorType errorType;

    public CustomNotFoundException(String message) {
        super(message);
    }

    public CustomNotFoundException() {
        this.errorType = ErrorType.NOT_FOUND_EXCEPTION;
    }

}
