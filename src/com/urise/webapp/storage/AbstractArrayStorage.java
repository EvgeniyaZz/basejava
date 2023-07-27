package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10000;
    protected int quantityResume;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];

    public final void clear() {
        Arrays.fill(storage, 0, quantityResume, null);
        quantityResume = 0;
    }

    public final void updateResume(Resume resume) {
        int index = getIndex(resume.getUuid());
        storage[index] = resume;
    }

    public final void saveResume(Resume resume) {
        if(quantityResume >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            insertElement(resume);
            quantityResume++;
        }
    }

    public final Resume getResume(String uuid) {
        return storage[getIndex(uuid)];
    }

    public final void deleteResume(String uuid) {
        fillDeletedElement(getIndex(uuid));
        storage[quantityResume - 1] = null;
        quantityResume--;
    }

    public final Resume[] getAll() {
        return Arrays.copyOf(storage, quantityResume);
    }

    public final int size() {
        return quantityResume;
    }

    protected abstract void insertElement(Resume resume);

    protected abstract void fillDeletedElement(int index);
}