package com.fable.assignment.serviceImpl;

import com.fable.assignment.entity.LogEntity;
import com.fable.assignment.service.LogService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlushLogsService {
    private static final Logger log = LoggerFactory.getLogger(FlushLogsService.class);

    @Autowired
    LogService service;
    @Value("${file.path}")
    private static final String filepath="/app/data/logs.txt";

    public void addLogsToDB() {
        try {
            Path filePath = Paths.get(filepath);
            List<String> logLines = Files.readAllLines(filePath);

            List<LogEntity> entities = new ArrayList<>();
            Gson gson = new Gson();

            for (String logLine : logLines) {
                System.out.println(logLine);
                LogEntity entity = gson.fromJson(logLine, LogEntity.class);
                System.out.println(entity.toString());
                if (entity.getLogId() != null) {
                    entities.add(entity);
                }
            }

            service.addLogs(entities);

            Files.write(filePath, new byte[0], StandardOpenOption.TRUNCATE_EXISTING);

            log.debug("Flushing the logs to the database now");
        } catch (IOException e) {
            log.error("An error occurred while flushing logs to the database", e);
        }
    }
}
