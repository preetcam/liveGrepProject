package com.test.LiveGrep.liveGrepProject.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Service
public class FileSearchService {

    public List<String> findMatchingLines(String directory, String regex, String dateRange) {
        Path dirPath = Paths.get(directory);
        List<String> matchedLines = new ArrayList<>();

        if (!Files.isDirectory(dirPath)) {
            throw new RuntimeException("Provided path is not a directory.");
        }

        // For the date range extraction
        String[] dateParts = dateRange.split("-");
        String startDate = dateParts[0].trim();
        String endDate = dateParts[1].trim();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath)) {
            for (Path entry : stream) {
                if (Files.isRegularFile(entry)) {
                    try (Stream<String> lines = Files.lines(entry)) {
                        lines.forEach(line -> {
                            if (DateRangeFilterService.isWithinDateRange(line, startDate, endDate) &&
                                lineMatchesRegex(line, regex)) {
                                matchedLines.add(line);
                            }
                        });
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading files: " + e.getMessage());
        }
        return matchedLines;
    }

    private boolean lineMatchesRegex(String line, String regex) {
        Pattern pattern = Pattern.compile(regex);
        String[] parts = line.split(" ");

        if (parts.length > 6) {
            StringBuilder logDetails = new StringBuilder();

            for (int i = 6; i < parts.length; i++) {
                logDetails.append(parts[i]).append(" ");  // Append each part and a space
            }

            return pattern.matcher(logDetails.toString().trim()).find(); // Trim to remove trailing space
        }

        return false;
    }

}
