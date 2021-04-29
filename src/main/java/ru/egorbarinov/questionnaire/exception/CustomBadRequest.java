package ru.egorbarinov.questionnaire.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomBadRequest extends RuntimeException {

    private ErrorType errorType;

    public CustomBadRequest(String message) {
        super(message);
    }

    public CustomBadRequest() {
        this.errorType = ErrorType.BAD_REQUEST;
    }
}
