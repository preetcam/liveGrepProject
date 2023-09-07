package com.test.LiveGrep.liveGrepProject.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// Not used now
public class DateRangeFilterService {

    public static boolean isWithinDateRange(String logLine, String startDateStr, String endDateStr) {
        SimpleDateFormat logDateFormat = new SimpleDateFormat("dd/MMM/yyyy");
       // LocalDate parsedStartDate = LocalDate.parse(startDateStr, DateTimeFormatter.ISO_DATE);

        String[] parts = logLine.split(" ");
        if (parts.length > 3) {
            String datePart = parts[3].substring(1);  // trim the starting [

            try {
                Date logDate = logDateFormat.parse(datePart);
                Date startDate = logDateFormat.parse(startDateStr);
                Date endDate = logDateFormat.parse(endDateStr);

                return logDate.after(startDate) && logDate.before(endDate);
            } catch (ParseException e) {

            }
        }
        return false;
    }
}
