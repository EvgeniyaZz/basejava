package com.urise.webapp.storage;
/**
 * Array based storage for Resumes
 */

import com.urise.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void insertElement(Resume resume) {
        storage[quantityResume] = resume;
    }

    @Override
    public void fillDeletedElement(int index) {
        storage[index] = storage[quantityResume - 1];
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < quantityResume; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}