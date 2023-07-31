package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public final void update(Resume resume) {
        doUpdate(resume, getNotExistingSearchKey(resume.getUuid()));
    }

    public final void save(Resume resume) {
        doSave(resume, getExistingSearchKey(resume));
    }

    public final Resume get(String uuid) {
        return doGet(getNotExistingSearchKey(uuid));
    }

    public final void delete(String uuid) {
        doDelete(uuid, getNotExistingSearchKey(uuid));
    }

    protected abstract boolean isExist(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract void doUpdate(Resume resume, Object searchKey);

    protected abstract void doSave(Resume resume, Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doDelete(String uuid, Object searchKey);

    private Object getExistingSearchKey(Resume resume) {
        Object searchKey = getSearchKey(resume.getUuid());
        if(isExist(searchKey)) {
            throw new ExistStorageException(resume.getUuid());
        }
        return searchKey;
    }

    private Object getNotExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if(!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }
}