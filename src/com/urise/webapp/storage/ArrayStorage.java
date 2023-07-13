package com.urise.webapp.storage;
/**
 * Array based storage for Resumes
 */
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage {

    private int quantityResume;
    Resume[] storage = new Resume[10000];

    public void clear() {
        Arrays.fill(storage, 0, quantityResume, null);
        quantityResume = 0;
    }

    public void update(Resume resume) {
        for(int i = 0; i < quantityResume; i++) {
            if(sameUuid(i, resume.getUuid())) {
                storage[i] = resume;
                return;
            }
        }
        System.out.println("Резюме " + resume.getUuid() + " не найдено");
    }

    public void save(Resume resume) {
        if(quantityResume >= 10000) {
            System.out.println("Резюме " + resume.getUuid() + " не возможно сохранить. Хранилище заполнено.");
            return;
        }
        if(quantityResume > 0) {
            for(int i = 0; i < quantityResume; i++) {
                if(sameUuid(i, resume.getUuid())) {
                    System.out.println("Резюме " + resume.getUuid() + " уже существует");
                    return;
                }
            }
        }
        storage[quantityResume++] = resume;
    }

    public Resume get(String uuid) {
        for(int i = 0; i < quantityResume; i++) {
            if(sameUuid(i, uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        for(int i = 0; i < quantityResume; i++) {
            if(sameUuid(i, uuid)) {
                if(i != storage.length - 1) {
                    System.arraycopy(storage, i + 1, storage, i, quantityResume - 1 - i);
                    storage[quantityResume - 1] = null;
                } else {
                    storage[i] = null;
                }
                quantityResume--;
                break;
            }
        }
        System.out.println("Резюме " + uuid + " не найдено");
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

    private boolean sameUuid(int i, String uuid) {
        return storage[i].getUuid().equals(uuid);
    }
}