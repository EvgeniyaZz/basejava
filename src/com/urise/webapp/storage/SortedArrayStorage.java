package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    protected void saveResume(Resume resume) {
        int index = (Arrays.binarySearch(storage, 0, quantityResume, resume) * -1) - 1;
        System.arraycopy(storage, index, storage, index + 1, quantityResume - index);
        storage[index] = resume;
    }

    @Override
    public void deleteResume(int index) {
        if(index < quantityResume - 1) {
            System.arraycopy(storage, index + 1, storage, index, quantityResume - 1 - index);
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, quantityResume, searchKey);
    }
}