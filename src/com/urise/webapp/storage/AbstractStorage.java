package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected static final Comparator<Resume> RESUME_COMPARATOR_FULL_NAME = Comparator.comparing(Resume::getFullName).
                                                                                       thenComparing(Resume::getUuid);

    public final void update(Resume resume) {
        doUpdate(resume, getExistingSearchKey(resume.getUuid()));
    }

    public final void save(Resume resume) {
        doSave(resume, getNotExistingSearchKey(resume));
    }

    public final Resume get(String uuid) {
        return doGet(getExistingSearchKey(uuid));
    }

    public final List<Resume> getAllSorted() {
        List<Resume> storage = new ArrayList<>(getNotSorted());
        storage.sort(RESUME_COMPARATOR_FULL_NAME);
        return storage;
    }

    public final void delete(String uuid) {
        doDelete(uuid, getExistingSearchKey(uuid));
    }

    private Object getNotExistingSearchKey(Resume resume) {
        Object searchKey = getSearchKey(resume.getUuid());
        if(isExist(searchKey)) {
            throw new ExistStorageException(resume.getUuid());
        }
        return searchKey;
    }

    private Object getExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if(!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract boolean isExist(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract void doUpdate(Resume resume, Object searchKey);

    protected abstract void doSave(Resume resume, Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doDelete(String uuid, Object searchKey);

    protected abstract List<Resume> getNotSorted();
}