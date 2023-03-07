package com.academy.controller;

import com.academy.dto.LogDto;
import com.academy.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: Maurizio Minieri
 * @email: mauminieri@gmail.com
 * @website: www.mauriziominieri.it
 */

@Api("REST Controller per le operazioni sui Log")
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    @ApiOperation("Restituisce tutti i log")
    @GetMapping
    public ResponseEntity<List<LogDto>> getAll() {
        return ResponseEntity.ok(logService.getAll());
    }

    @ApiOperation("Restituisce tutti i log da una data")
    @GetMapping(value = "/fromDate/{fromDate}")
    public ResponseEntity<List<LogDto>> getDateAfter(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate) {
        return ResponseEntity.ok(logService.getDateAfter(fromDate));
    }

    @ApiOperation("Restituisce tutti i log fino ad una data")
    @GetMapping(value = "/toDate/{toDate}")
    public ResponseEntity<List<LogDto>> getDateBefore(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(toDate);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return ResponseEntity.ok(logService.getDateBefore(calendar.getTime()));
    }

    @ApiOperation("Restituisce tutti i log in un range di date")
    @GetMapping(value = "/between/{fromDate}/{toDate}")
    public ResponseEntity<List<LogDto>> getDateBetween(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(toDate);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return ResponseEntity.ok(logService.getDateBetween(fromDate, calendar.getTime()));
    }
}