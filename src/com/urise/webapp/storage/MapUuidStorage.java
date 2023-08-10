package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Map;

public class MapUuidStorage extends AbstractMapStorage {

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        STORAGE.put((String) searchKey, resume);
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        STORAGE.put((String) searchKey, resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return STORAGE.get((String) searchKey);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        for(Map.Entry<String, Resume> entry : STORAGE.entrySet()) {
            if(searchKey.equals(entry.getKey())) {
                return true;
            }
        }
        return false;
    }
}