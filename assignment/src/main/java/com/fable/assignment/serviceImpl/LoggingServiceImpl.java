package com.fable.assignment.serviceImpl;

import com.fable.assignment.dto.LoggerApiInput;
import com.fable.assignment.service.LoggingService;
import com.fable.assignment.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Map;


@Service
public class LoggingServiceImpl implements LoggingService {
    @Autowired
    FlushLogsService flushLogsService;
    @Autowired
    ObjectUtils objectUtils;

    @Value("${file.path}")
    private static final String filepath="/app/data/logs.txt";
    private static final Logger log = LoggerFactory.getLogger(LoggingServiceImpl.class);

    @Override
    @Async
    public void addLogs(Map<String, Object> payload) {
        sanitizePayload(payload);
        log.info("Payload: {}", payload.toString());

        try {
            log.info("Writing to the file now");
            File file = new File(filepath);

            try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
                writer.println(payload.toString());
            }

            if (getFileSizeMegaBytes(file) >= 10) {
                flushLogsService.addLogsToDB();
            }
        } catch (IOException e) {
            log.error("An error occurred while writing logs to the file", e);
        }
    }
    private static void sanitizePayload(Map<String, Object> payload) {
        payload.put("logId", payload.get("id"));
        payload.remove("id");
    }

    private static double getFileSizeMegaBytes(File file) {
        return file.length() / (1024 * 1024);
    }
}
