package com.urise.webapp.storage;
/**
 * Array based storage for Resumes
 */

import com.urise.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage{

    @Override
    public void saveResume(Resume resume) {
        storage[quantityResume] = resume;
    }

    @Override
    public void deleteResume(int index) {
            storage[index] = storage[quantityResume - 1];
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    @Override
    protected int getIndex(String uuid) {
        for(int i = 0; i < quantityResume; i++) {
            if(storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}