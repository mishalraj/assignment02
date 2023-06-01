package com.fable.assignment.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public interface LoggingService {
    public void addLogs(Map<String, Object> o) throws IOException;
}
