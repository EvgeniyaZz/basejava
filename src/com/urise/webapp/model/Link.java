package com.urise.webapp.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Link implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String name;
    private final String website;

    public Link(String name, String website) {
        Objects.requireNonNull(name, "name must not be null");
        this.name = name;
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return Objects.equals(name, link.name) && Objects.equals(website, link.website);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, website);
    }

    @Override
    public String toString() {
        return "Link{" +
                "name='" + name + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}