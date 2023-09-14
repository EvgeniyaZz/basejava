package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());
    protected static final Comparator<Resume> RESUME_COMPARATOR_FULL_NAME = Comparator.comparing(Resume::getFullName).
                                                                                       thenComparing(Resume::getUuid);
    public final void update(Resume resume) {
        LOG.info("Update " + resume);
        doUpdate(resume, getExistingSearchKey(resume.getUuid()));
    }

    public final void save(Resume resume) {
        LOG.info("Save " + resume);
        doSave(resume, getNotExistingSearchKey(resume));
    }

    public final Resume get(String uuid) {
        LOG.info("Get " + uuid);
        return doGet(getExistingSearchKey(uuid));
    }

    public final List<Resume> getAllSorted() {
        LOG.info("GetAllSorted");
        List<Resume> storage = new ArrayList<>(getAll());
        storage.sort(RESUME_COMPARATOR_FULL_NAME);
        return storage;
    }

    public final void delete(String uuid) {
        LOG.info("Delete " + uuid);
        doDelete(getExistingSearchKey(uuid));
    }

    private SK getNotExistingSearchKey(Resume resume) {
        SK searchKey = getSearchKey(resume.getUuid());
        if(isExist(searchKey)) {
            LOG.warning("Resume " + resume.getUuid() + " already exist");
            throw new ExistStorageException(resume.getUuid());
        }
        return searchKey;
    }

    private SK getExistingSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if(!isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract boolean isExist(SK searchKey);

    protected abstract SK getSearchKey(String uuid);

    protected abstract void doUpdate(Resume resume, SK searchKey);

    protected abstract void doSave(Resume resume, SK searchKey);

    protected abstract Resume doGet(SK searchKey);

    protected abstract void doDelete(SK searchKey);

    protected abstract List<Resume> getAll();
}