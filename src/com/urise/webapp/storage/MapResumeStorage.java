package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Map;

public class MapResumeStorage extends MapStorage {

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        STORAGE.put(resume.getUuid(), resume);
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        STORAGE.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return new Resume(uuid);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        for(Map.Entry<String, Resume> entry : STORAGE.entrySet()) {
            if(searchKey.equals(entry.getValue())) {
                return true;
            }
        }
        return false;
    }
}