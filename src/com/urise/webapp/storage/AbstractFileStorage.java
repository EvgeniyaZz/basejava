package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private final File directory;

    public AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    public void clear() {
        String[] list = directory.list();
        if (list != null) {
            for (String s : list) {
                File file = new File(directory + File.separator + s);
                doDelete(s, file);
            }
        } else {
            throw new StorageException(directory.getAbsolutePath() + " is not directory or not readable/writable", null);
        }
    }

    @Override
    protected void doUpdate(Resume resume, File file) {
        try {
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("File write error", file.getName(), e);
        }
    }

    @Override
    protected void doSave(Resume resume, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Couldn't create file" + file.getAbsolutePath(), file.getName(), e);
        }
        doUpdate(resume, file);
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return doRead(file);
        } catch (IOException e) {
            throw new StorageException("File read error", file.getName(), e);
        }
    }

    @Override
    protected void doDelete(String uuid, File file) {
        if(!file.delete()) {
            throw new StorageException("File delete error", file.getName());
        }
    }

    @Override
    protected List<Resume> getAll() {
        List<Resume> allResume = new ArrayList<>();
        String[] list = directory.list();
        if (list != null) {
            for (String s : list) {
                File file = new File(directory + File.separator + s);
                allResume.add(doGet(file));
            }
        } else {
            throw new StorageException(directory.getAbsolutePath() + " is not directory or not readable/writable", null);
        }
        return allResume;
    }

    @Override
    public int size() {
        String[] list = directory.list();
        if (list != null) {
            return list.length;
        } else {
            throw new StorageException(directory.getAbsolutePath() + " is not directory or not readable/writable", null);
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    protected abstract void doWrite(Resume resume, File file) throws IOException;

    protected abstract Resume doRead(File file) throws IOException;
}