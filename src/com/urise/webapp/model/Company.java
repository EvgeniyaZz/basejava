package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class Company {

    private final Link homePage;
    private final List<Period> period;

    public Company(String name, String website, List<Period> period) {
        this.homePage = new Link(name, website);
        this.period = period;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(homePage, company.homePage) && Objects.equals(period, company.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, period);
    }

    @Override
    public String toString() {
        return "Company{" +
                "homePage=" + homePage +
                ", period=" + period +
                '}';
    }
}