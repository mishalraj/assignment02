package com.fable.assignment.service;

import com.fable.assignment.entity.LogEntity;

import java.util.List;


public interface LogService {
    void addLog(LogEntity log);
    void addLogs(List<LogEntity> logs);
}
