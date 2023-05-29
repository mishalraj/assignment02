package com.fable.assignment.dto;

import lombok.Data;

@Data
public class LoggerApiOutput {
    boolean isSuccess;
    String message;
    String errorCode;
}
