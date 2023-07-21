package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10000;
    protected int quantityResume;
    public final Resume[] storage = new Resume[STORAGE_LIMIT];

    public final void clear() {
        Arrays.fill(storage, 0, quantityResume, null);
        quantityResume = 0;
    }

    public final void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if(isExist(index)) {
            storage[index] = resume;
            return;
        }
        throw new NotExistStorageException(resume.getUuid());
    }

    public final void save(Resume resume) {
        if(quantityResume >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else if(isExist(getIndex(resume.getUuid()))) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            saveResume(resume);
            quantityResume++;
        }
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if(isExist(index)) {
            deleteResume(index);
            storage[quantityResume - 1] = null;
            quantityResume--;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public final Resume[] getAll() {
        return Arrays.copyOf(storage, quantityResume);
    }

    public final int size() {
        return quantityResume;
    }

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if(isExist(index)) {
            return storage[index];
        }
        throw new NotExistStorageException(uuid);
    }

    protected final boolean isExist(int index) {
        return index >= 0;
    }

    protected abstract void saveResume(Resume resume);

    protected abstract void deleteResume(int index);

    protected abstract int getIndex(String uuid);
}
