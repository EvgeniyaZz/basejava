package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Map;

public class MapUuidStorage extends AbstractMapStorage<String> {

    @Override
    protected void doUpdate(Resume resume, String searchKey) {
        STORAGE.put(searchKey, resume);
    }

    @Override
    protected void doSave(Resume resume, String searchKey) {
        STORAGE.put(searchKey, resume);
    }

    @Override
    protected Resume doGet(String searchKey) {
        return STORAGE.get(searchKey);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(String searchKey) {
        for(Map.Entry<String, Resume> entry : STORAGE.entrySet()) {
            if(searchKey.equals(entry.getKey())) {
                return true;
            }
        }
        return false;
    }
}