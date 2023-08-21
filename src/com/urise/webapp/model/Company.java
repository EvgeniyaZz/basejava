package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class Company {
    private final String name;
    private final String website;
    private final List<Period> period;

    public Company(String name, String website, List<Period> period) {
        this.name = name;
        this.website = website;
        this.period = period;
    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }

    public List<Period> getPeriod() {
        return period;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(name, company.name)
               && Objects.equals(website, company.website)
               && Objects.equals(period, company.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, website, period);
    }

    @Override
    public String toString() {
        return name + " " + website + "\n" + period + "\n";
    }
}