package com.test.LiveGrep.liveGrepProject.controller;

import com.test.LiveGrep.liveGrepProject.service.LogFileSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LogSearchController {

    @Autowired
    private final LogFileSearchService service;

    public LogSearchController(LogFileSearchService service) {
        this.service = service;
    }

    @GetMapping("/searchLogs")
    public List<String> searchLogs(
            @RequestParam String directory,
            @RequestParam String regex,
            @RequestParam String dateRange) {
        return service.searchLogs(directory, regex, dateRange);
    }
}
