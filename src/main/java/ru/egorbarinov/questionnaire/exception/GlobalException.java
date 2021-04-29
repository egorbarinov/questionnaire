package ru.egorbarinov.questionnaire.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GlobalException extends RuntimeException {

    private ErrorType errorType;

    public GlobalException(String message) {
        super(message);
    }

    public GlobalException() {
        this.errorType = ErrorType.GLOBAL_EXCEPTION;
    }

}

