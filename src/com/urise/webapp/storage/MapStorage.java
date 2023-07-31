package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;

public class MapStorage extends AbstractStorage {

    protected static final HashMap<Object, Resume> STORAGE = new HashMap<>();

    @Override
    public void clear() {
        STORAGE.clear();
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        STORAGE.put(searchKey, resume);
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        STORAGE.put(searchKey, resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return STORAGE.get(searchKey);
    }

    @Override
    protected void doDelete(String uuid, Object searchKey) {
        STORAGE.remove(uuid);
    }

    @Override
    public Resume[] getAll() {
        return STORAGE.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return STORAGE.size();
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return STORAGE.get(searchKey) != null;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }
}