package com.urise.webapp.model;

import java.util.Objects;

public class Period {
    private final String startDate;
    private final String endDate;
    private String name;
    private final String description;

    public Period(String startDate, String endDate, String description) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }
    public Period(String startDate, String endDate, String name, String description) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return Objects.equals(startDate, period.startDate)
               && Objects.equals(endDate, period.endDate)
               && Objects.equals(name, period.name)
               && Objects.equals(description, period.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, name, description);
    }

    @Override
    public String toString() {
        if(name == null) {
            return startDate + " - " + endDate + "   " + description;
        }
        return startDate + " - " + endDate + "   " + name + "\n" + description;
    }
}