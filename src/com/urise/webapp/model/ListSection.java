package com.urise.webapp.model;

import java.io.Serial;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSection extends Section {
    @Serial
    private static final long serialVersionUID = 1L;

    private final List<String> list;

    public ListSection(String... list) {
        this(Arrays.asList(list));
    }

    public ListSection(List<String> list) {
        Objects.requireNonNull(list, "list must not be null");
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }

    @Override
    public String toString() {
        return list.toString();
    }
}