package ru.egorbarinov.questionnaire.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ResponseMessage {

    String status;
    String msg;
    String path;
    String method;
    Date time;
    String info;

}
