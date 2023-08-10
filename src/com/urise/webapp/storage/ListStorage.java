package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private static final List<Resume> STORAGE = new ArrayList<>();

    @Override
    public void clear() {
        STORAGE.clear();
    }

    @Override
    public void doUpdate(Resume resume, Object searchKey) {
        STORAGE.set((Integer) searchKey, resume);
    }

    @Override
    public void doSave(Resume resume, Object searchKey) {
        STORAGE.add(resume);
    }

    @Override
    public Resume doGet(Object searchKey) {
        return STORAGE.get((Integer) searchKey);
    }

    @Override
    public void doDelete(String uuid, Object searchKey) {
        STORAGE.remove(get(uuid));
    }

    @Override
    public Resume[] getAll() {
        return STORAGE.toArray(new Resume[0]);
    }

    @Override
    public List<Resume> getNotSorted() {
        return STORAGE;
    }

    @Override
    public int size() {
        return STORAGE.size();
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (int) searchKey >= 0;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        for(int i = 0; i < STORAGE.size(); i++) {
            if(STORAGE.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}