package com.fable.assignment.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fable.assignment.serviceImpl.FlushLogsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledLogs {
    @Autowired
    FlushLogsService logs;
    private static final Logger log = LoggerFactory.getLogger(ScheduledLogs.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Async
    @Scheduled(fixedRate = 30000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        logs.addLogsToDB();
    }
}