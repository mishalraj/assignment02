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
    private static final String filepath="/data/logs.txt";
    private static final Logger log = LoggerFactory.getLogger(LoggingServiceImpl.class);

    @Override
    @Async
    public void addLogs(Map<String, Object> payload) {
        synchronized(payload) {
            LoggerApiInput loggerApiInput = objectUtils.convertToLoggerInput(payload);
            try {
                log.info("Writing to the file now");
                File file = new File(filepath);
                FileWriter fr = new FileWriter(file, true);
                PrintWriter br = new PrintWriter(fr);
                br.println(loggerApiInput.toString());
                if (getFileSizeMegaBytes(file) >= 10) {
                    flushLogsService.addLogsToDB();
                }
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static double getFileSizeMegaBytes(File file) {
        return file.length() / (1024 * 1024);
    }
}
