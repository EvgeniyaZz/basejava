package com.urise.webapp.storage;

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
        System.out.println("Резюме " + resume.getUuid() + " не найдено");
    }

    public final void save(Resume resume) {
        if(quantityResume >= STORAGE_LIMIT) {
            System.out.println("Резюме " + resume.getUuid() + " не возможно сохранить. Хранилище заполнено.");
        } else if(isExist(getIndex(resume.getUuid()))) {
            System.out.println("Резюме " + resume.getUuid() + " уже существует");
        } else {
            saveResume(resume);
            quantityResume++;
        }
    }

    protected abstract void saveResume(Resume resume);

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if(isExist(index)) {
            deleteResume(index);
            storage[quantityResume - 1] = null;
            quantityResume--;
        } else {
            System.out.println("Резюме " + uuid + " не найдено");
        }
    }

    protected abstract void deleteResume(int index);

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
        System.out.println("Резюме " + uuid + " не существует.");
        return null;
    }

    protected final boolean isExist(int index) {
        return index >= 0;
    }

    protected abstract int getIndex(String uuid);
}
