package com.fable.assignment.serviceImpl;

import com.fable.assignment.entity.LogEntity;
import com.fable.assignment.repo.LogRepo;
import com.fable.assignment.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    LogRepo repo;
    @Override
    public void addLog(LogEntity log) {
        repo.save(log);
    }
    @Override
    public void addLogs(List<LogEntity> logs) {
        repo.saveAll(logs);
    }
}
