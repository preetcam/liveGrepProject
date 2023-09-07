package com.test.LiveGrep.liveGrepProject.service;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class DateRangeUtil {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MMM/yyyy");
    private static final Pattern DATE_PATTERN = Pattern.compile("\\[(\\d{2}/[a-zA-Z]{3}/\\d{4}):");

    public boolean isWithinRange(String line, String dateRange) {
        if (dateRange == null) return true;

        Matcher m = DATE_PATTERN.matcher(line);
        if (m.find()) {
            String dateStr = m.group(1);
            String[] range = dateRange.split("-");
            if (range.length != 2) {
                throw new IllegalArgumentException("Date range format must be StartDate-EndDate");
            }
            try {
                Date date = FORMAT.parse(dateStr);
                Date start = FORMAT.parse(range[0]);
                Date end = FORMAT.parse(range[1]);
                return !date.before(start) && !date.after(end);
            } catch (ParseException e) {
                return false;
            }
        }
        return false;
    }
}
