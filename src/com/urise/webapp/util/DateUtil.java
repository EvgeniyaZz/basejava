package com.urise.webapp.util;

import java.time.LocalDate;
import java.time.Month;

public class DateUtil {
    public LocalDate of(int year, Month month) {
        return LocalDate.of(year, month, 1);
    }
}