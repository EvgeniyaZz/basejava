package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    private static final List<Resume> STORAGE = new ArrayList<>();

    @Override
    public void clear() {
        STORAGE.clear();
    }

    @Override
    public void doUpdate(Resume resume, Integer searchKey) {
        STORAGE.set(searchKey, resume);
    }

    @Override
    public void doSave(Resume resume, Integer searchKey) {
        STORAGE.add(resume);
    }

    @Override
    public Resume doGet(Integer searchKey) {
        return STORAGE.get(searchKey);
    }

    @Override
    public void doDelete(Integer searchKey) {
        STORAGE.remove(searchKey.intValue());
    }

    @Override
    public List<Resume> getAll() {
        return STORAGE;
    }

    @Override
    public int size() {
        return STORAGE.size();
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for(int i = 0; i < STORAGE.size(); i++) {
            if(STORAGE.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}