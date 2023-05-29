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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlushLogsService {
    private static final Logger log = LoggerFactory.getLogger(FlushLogsService.class);

    @Autowired
    LogService service;
    @Value("${file.path}")
    private static final String filepath="/data/logs.txt";

    @Async
    public void addLogsToDB() throws IOException {
        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(filepath));
            BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            List<LogEntity> entities = new ArrayList<>();
            String line = reader.readLine();
            while (line != null) {
                Gson gson = new Gson();
                LogEntity entity = gson.fromJson(line, LogEntity.class);
                entities.add(entity);
                line = reader.readLine();
            }
            service.addLogs(entities);
            synchronized (writer) {
                log.debug("flushing the logs to db now");
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
