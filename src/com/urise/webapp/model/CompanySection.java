package com.urise.webapp.model;

import java.util.List;

public class CompanySection extends Section {
    private final List<Company> companies;

    public CompanySection(List<Company> data) {
        this.companies = data;
    }

    @Override
    public String toString() {
        return companies.toString();
    }
}