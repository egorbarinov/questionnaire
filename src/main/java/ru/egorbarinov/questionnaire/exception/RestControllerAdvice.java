package ru.egorbarinov.questionnaire.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomBadRequest.class)
    public ResponseMessage exceptionHandler(HttpServletRequest request, CustomBadRequest exception) {
        return getResponseMessage(request, exception.getErrorType(), exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(GlobalException.class)
    public ResponseMessage exceptionHandler(HttpServletRequest request, GlobalException exception) {
        return getResponseMessage(request, exception.getErrorType(), exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseMessage exceptionHandler(HttpServletRequest request, CustomNotFoundException exception) {
        return getResponseMessage(request, exception.getErrorType(), exception.getMessage());
    }

    private ResponseMessage getResponseMessage(HttpServletRequest request, ErrorType errorType,
        String info) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setTime(new Date());
        responseMessage.setStatus(errorType.getCode());
        responseMessage.setMsg(errorType.getMsg());
        responseMessage.setMethod(request.getMethod());
        responseMessage
            .setPath(request.getRequestURL().toString() + "?" + request.getQueryString());
        responseMessage.setInfo(info);
        return responseMessage;
    }


}
