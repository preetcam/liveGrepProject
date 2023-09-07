package com.test.LiveGrep.liveGrepProject.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class RegexFilterService {

    private static final String LOG_DIR_PATH = "NASA_access_log";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<String> filterLogsByDateAndRegex(LocalDate startDate, LocalDate endDate, String regex) {
        List<String> filteredLogs = new ArrayList<>();

        try {
            // Get the path to the NASA_access_log directory inside resources
            Path folderPath = Paths.get(new ClassPathResource(LOG_DIR_PATH).getURI());

            // List all files inside the directory
            Files.list(folderPath).forEach(file -> {
                try (BufferedReader reader = new BufferedReader(new FileReader(file.toFile()))) {
                    Pattern datePattern = Pattern.compile("^(\\d{4}-\\d{2}-\\d{2})");
                    Pattern regexPattern = Pattern.compile(regex);

                    filteredLogs.addAll(
                        reader.lines()
                              .filter(line -> {
                                  Matcher dateMatcher = datePattern.matcher(line);
                                  if (dateMatcher.find()) {
                                      LocalDate logDate = LocalDate.parse(dateMatcher.group(1), FORMATTER);
                                      return !logDate.isBefore(startDate) && !logDate.isAfter(endDate);
                                  }
                                  return false;
                              })
                              .filter(line -> regexPattern.matcher(line).find())
                              .collect(Collectors.toList())
                    );

                } catch (IOException e) {
                    // Handle or throw the exception based on your requirements.
                    e.printStackTrace();
                }
            });

        } catch (IOException e) {
            // Handle or throw the exception based on your requirements.
            e.printStackTrace();
        }

        return filteredLogs;
    }

    public boolean matches(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

}
