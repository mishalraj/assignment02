package com.fable.assignment.util;

import com.fable.assignment.dto.LoggerApiInput;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ObjectUtils {
    public LoggerApiInput convertToLoggerInput(Map<String, Object> payload){
        ObjectMapper mapper = new ObjectMapper();
        LoggerApiInput input = mapper.convertValue(payload , LoggerApiInput.class);
        return input;
    }
}
