package com.xm.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class DateUtils {
    private static final Logger logger = LogManager.getLogger(DateUtils.class);
    private static final LocalDate TODAY_DATE = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E MMM dd yyyy");

    public String getTodayDate() {
        String today = formatter.format(TODAY_DATE);
        logger.info("Date is %s".formatted(today));
        return today;
    }

    public String getTomorrowDate() {
        LocalDate tomorrowDate = TODAY_DATE.plusDays(1);
        String tomorrow = tomorrowDate.format(formatter);
        logger.info("Date is %s".formatted(tomorrow));
        return tomorrow;
    }

    public String getFirstDayOfThisWeek() {
        LocalDate firstDay = TODAY_DATE.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        String firstDayOfTheWeek = firstDay.format(formatter);
        logger.info("First day of the current week is %s".formatted(firstDayOfTheWeek));
        return firstDayOfTheWeek;
    }

    public String getFirstDayOfTheNextWeek() {
        LocalDate firstDay = TODAY_DATE.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        String firstDayOfTheWeek = firstDay.format(formatter);
        logger.info("First day of the next week is %s".formatted(firstDayOfTheWeek));
        return firstDayOfTheWeek;
    }

    public String getLastDayOfTheWeek(boolean thisWeek) {
        LocalDate firstDay;
        if (thisWeek) {
            firstDay = TODAY_DATE.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        } else {
            firstDay = TODAY_DATE.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        }
        LocalDate lastDay = firstDay.plusDays(6);
        String lastDayOfTheWeek = lastDay.format(formatter);
        logger.info("Last day of the week is %s".formatted(lastDayOfTheWeek));
        return lastDayOfTheWeek;
    }

    public String getFirstDayOfTheMonth(int monthsToAdd) {
        LocalDate month = TODAY_DATE.plusMonths(monthsToAdd);
        LocalDate firstDay = month.withDayOfMonth(1);
        String firstDayOfMonth = firstDay.format(formatter);
        logger.info("First day of the month is %s".formatted(firstDayOfMonth));
        return firstDayOfMonth;
    }

    public String getLastDayOfTheMonth(int monthsToAdd) {
        LocalDate month = TODAY_DATE.plusMonths(monthsToAdd);
        LocalDate lastDay = month.withDayOfMonth(month.lengthOfMonth());
        String lastDayOfMonth = lastDay.format(formatter);
        logger.info("Last day of the month is %s".formatted(lastDayOfMonth));
        return lastDayOfMonth;
    }

}