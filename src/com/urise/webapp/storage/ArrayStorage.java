package com.urise.webapp.storage;
/**
 * Array based storage for Resumes
 */
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage {

    private int quantityResume;
    protected final Resume[] storage = new Resume[10000];

    public void clear() {
        Arrays.fill(storage, 0, quantityResume, null);
        quantityResume = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if(isExist(index)) {
            storage[index] = resume;
            return;
        }
        System.out.println("Резюме " + resume.getUuid() + " не найдено");
    }

    public void save(Resume resume) {
        if(quantityResume >= 10000) {
            System.out.println("Резюме " + resume.getUuid() + " не возможно сохранить. Хранилище заполнено.");
            return;
        }
        if(quantityResume > 0) {
            if(isExist(getIndex(resume.getUuid()))) {
                System.out.println("Резюме " + resume.getUuid() + " уже существует");
                return;
            }
        }
        storage[quantityResume++] = resume;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if(isExist(index)) {
            return storage[index];
        }
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if(isExist(index)) {
            storage[index] = storage[quantityResume - 1];
            storage[quantityResume - 1] = null;
            quantityResume--;
        } else {
            System.out.println("Резюме " + uuid + " не найдено");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, quantityResume);
    }

    public int size() {
        return quantityResume;
    }

    protected int getIndex(String uuid) {
        for(int i = 0; i < quantityResume; i++) {
            if(storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    protected boolean isExist(int index) {
        return index >= 0;
    }
}