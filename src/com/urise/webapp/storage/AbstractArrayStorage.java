package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

    protected static final int STORAGE_LIMIT = 10000;
    protected int quantityResume;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];

    public final void clear() {
        Arrays.fill(storage, 0, quantityResume, null);
        quantityResume = 0;
    }

    public final void doUpdate(Resume resume, Integer searchKey) {
        storage[searchKey] = resume;
    }

    public final void doSave(Resume resume, Integer searchKey) {
        if(quantityResume >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            insertElement(resume);
            quantityResume++;
        }
    }

    public final Resume doGet(Integer searchKey) {
        return storage[searchKey];
    }

    public final void doDelete(Integer searchKey) {
        fillDeletedElement(searchKey);
        storage[quantityResume - 1] = null;
        quantityResume--;
    }

    @Override
    public final List<Resume> getAll() {
        return new ArrayList<>(Arrays.asList(Arrays.copyOf(storage, quantityResume)));
    }

    public final int size() {
        return quantityResume;
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }

    protected abstract void insertElement(Resume resume);

    protected abstract void fillDeletedElement(int index);
}