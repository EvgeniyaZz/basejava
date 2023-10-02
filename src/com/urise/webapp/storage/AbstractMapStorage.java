package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMapStorage<Object> extends AbstractStorage<Object> {

    protected static final Map<String, Resume> STORAGE = new HashMap<>();

    @Override
    public void clear() {
        STORAGE.clear();
    }

    @Override
    public List<Resume> getAll() {
        return new ArrayList<>(STORAGE.values());
    }

    @Override
    public int size() {
        return STORAGE.size();
    }
}