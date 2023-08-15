package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

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
    protected void doDelete(String uuid, Object searchKey) {
        STORAGE.remove(uuid);
    }

    @Override
    public List<Resume> getNotSorted() {
        return STORAGE.values().stream().toList();
    }

    @Override
    public int size() {
        return STORAGE.size();
    }
}