package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public final void update(Resume resume) {
        if(!isExist(getIndex(resume.getUuid()))) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            updateResume(resume);
        }
    }

    public final void save(Resume resume) {
        if(isExist(getIndex(resume.getUuid()))) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            saveResume(resume);
        }
    }

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if(!isExist(index)) {
            throw new NotExistStorageException(uuid);
        } else {
            return getResume(uuid);
        }
    }

    public final void delete(String uuid) {
        if(!isExist(getIndex(uuid))) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteResume(uuid);
        }
    }
    protected final boolean isExist(int index) {
        return index >= 0;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void updateResume(Resume resume);

    protected abstract void saveResume(Resume resume);

    protected abstract Resume getResume(String uuid);

    protected abstract void deleteResume(String uuid);
}