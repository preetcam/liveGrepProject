package com.test.LiveGrep.liveGrepProject.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.*;
import java.util.regex.*;

@Service
public class FileProcessor {

    private final DateRangeUtil dateRangeUtil;

    public FileProcessor(DateRangeUtil dateRangeUtil) {
        this.dateRangeUtil = dateRangeUtil;
    }

    public void process(String directory, String regex, String dateRange) {
        try {
            Files.walk(Paths.get(directory))
                .filter(Files::isRegularFile)
                .forEach(filePath -> processFile(filePath, regex, dateRange));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processFile(Path filePath, String regex, String dateRange) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (dateRangeUtil.isWithinRange(line, dateRange)) {
                    // Remove date portion from line for regex matching
                    String withoutDate = line.replaceAll("\\[\\d{2}/[a-zA-Z]{3}/\\d{4}.*?\\]", "");
                    if (Pattern.compile(regex).matcher(withoutDate).find()) {
                        System.out.println(line);
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
