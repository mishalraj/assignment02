package com.fable.assignment.controller;

import com.fable.assignment.dto.LoggerApiOutput;
import com.fable.assignment.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/logger")
public class LogController {
    @Autowired
    LoggingService loggingService;

    @PostMapping(value="/log")
    public LoggerApiOutput logInput(@RequestBody Map<String, Object> payload){
        LoggerApiOutput output = new LoggerApiOutput();
        try {
            loggingService.addLogs(payload);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        output.setSuccess(true);
        output.setMessage("Successfully Added the log");
        return output;
    }
}
