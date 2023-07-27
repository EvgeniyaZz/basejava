package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {

    private static final ArrayList<Resume> STORAGE = new ArrayList<>();

    @Override
    public void clear() {
        STORAGE.clear();
        STORAGE.trimToSize();
    }

    @Override
    public void updateResume(Resume resume) {
        STORAGE.set(getIndex(resume.getUuid()), resume);
    }

    @Override
    public void saveResume(Resume resume) {
        STORAGE.add(resume);
    }

    @Override
    public Resume getResume(String uuid) {
        for(Resume r : STORAGE) {
            if(uuid.equals(r.getUuid())) {
                return r;
            }
        }
        return null;
    }

    @Override
    public void deleteResume(String uuid){
        STORAGE.remove(get(uuid));
    }

    @Override
    public Resume[] getAll() {
        return STORAGE.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return STORAGE.size();
    }

    @Override
    protected int getIndex(String uuid) {
        return STORAGE.indexOf(getResume(uuid));
    }
}