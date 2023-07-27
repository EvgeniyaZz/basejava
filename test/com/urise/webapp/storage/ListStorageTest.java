package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ListStorageTest {

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_NOT_EXIST = "dummy";

    private static final Resume R1 = new Resume(UUID_1);
    private static final Resume R2 = new Resume(UUID_2);
    private static final Resume R3 = new Resume(UUID_3);

    private static final Storage STORAGE = new ListStorage();

    @Before
    public void setUp() {
        STORAGE.clear();
        STORAGE.save(R1);
        STORAGE.save(R2);
        STORAGE.save(R3);
    }

    @Test
    public void clear() {
        STORAGE.clear();
        assertSize(0);
    }

    @Test
    public void update() {
        Resume R4 = new Resume(UUID_1);
        STORAGE.update(R4);
        assertEquals(R4, STORAGE.get(UUID_1));
    }

    @Test
    public void save() {
        Resume r4 = new Resume(UUID_4);
        STORAGE.save(r4);
        assertEquals(r4, STORAGE.get(UUID_4));
        assertGet(r4);
        assertSize(4);
    }

    @Test
    public void get() {
        assertGet(R1);
        assertGet(R2);
        assertGet(R3);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        STORAGE.delete(UUID_1);
        assertSize(2);
        STORAGE.get(UUID_1);
    }

    @Test
    public void getAll() {
        Resume[] allResumes = STORAGE.getAll();
        assertEquals(3, allResumes.length);
        assertEquals(R1, allResumes[0]);
        assertEquals(R2, allResumes[1]);
        assertEquals(R3, allResumes[2]);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        STORAGE.delete(UUID_NOT_EXIST);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        STORAGE.update(STORAGE.get(UUID_NOT_EXIST));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        STORAGE.save(R1);
    }

    private void assertSize(int size) {
        assertEquals(size, STORAGE.size());
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, STORAGE.get(resume.getUuid()));
    }
}