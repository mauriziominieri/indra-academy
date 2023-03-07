package com.academy.service;

import com.academy.dto.LogDto;
import com.academy.mapper.LogMapper;
import com.academy.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: Maurizio Minieri
 * @email: mauminieri@gmail.com
 * @website: www.mauriziominieri.it
 */

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private LogMapper logMapper;

    public List<LogDto> getAll() {
        return logMapper.toDtos(logRepository.findAllByOrderByLogDateDesc());
    }

    public List<LogDto> getDateAfter(Date fromDate) {
        return logMapper.toDtos(logRepository.findByLogDateAfterOrderByLogDateDesc(fromDate));
    }

    public List<LogDto> getDateBefore(Date toDate) {
        return logMapper.toDtos(logRepository.findByLogDateBeforeOrderByLogDateDesc(toDate));
    }

    public List<LogDto> getDateBetween(Date fromDate, Date toDate) {
        return logMapper.toDtos(logRepository.findByLogDateBetweenOrderByLogDateDesc(fromDate, toDate));
    }

    public LogDto add(LogDto logDto) {
        return logMapper.toDto(logRepository.save(logMapper.toModel(logDto)));
    }
}