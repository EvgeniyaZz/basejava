package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10000;
    protected int quantityResume;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];

    public final void clear() {
        Arrays.fill(storage, 0, quantityResume, null);
        quantityResume = 0;
    }

    public final void doUpdate(Resume resume, Object searchKey) {
        storage[(int) searchKey] = resume;
    }

    public final void doSave(Resume resume, Object searchKey) {
        if(quantityResume >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            insertElement(resume);
            quantityResume++;
        }
    }

    public final Resume doGet(Object searchKey) {
        return storage[(int) searchKey];
    }

    public final void doDelete(String uuid, Object searchKey) {
        fillDeletedElement((int) searchKey);
        storage[quantityResume - 1] = null;
        quantityResume--;
    }

    public final Resume[] getAll() {
        return Arrays.copyOf(storage, quantityResume);
    }

    public final List<Resume> getAllSorted() {
        Arrays.sort(storage, 0, quantityResume, RESUME_COMPARATOR_FULL_NAME);
        return List.of();
    }

    public final int size() {
        return quantityResume;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (int) searchKey >= 0;
    }

    protected abstract void insertElement(Resume resume);

    protected abstract void fillDeletedElement(int index);
}