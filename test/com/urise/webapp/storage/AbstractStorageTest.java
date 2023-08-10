package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractStorageTest {

    private static final String FULL_NAME_1 = "Захарова Евгения";
    private static final String FULL_NAME_2 = "Макаров Александр";
    private static final String FULL_NAME_3 = "Губина Вероника";
    private static final String FULL_NAME_4 = "Громов Леонид";
    private static final String UUID_NOT_EXIST = "dummy";

    protected final Storage storage;
    protected final Resume r1 = new Resume(FULL_NAME_1);
    protected final Resume r2 = new Resume(FULL_NAME_2);
    protected final Resume r3 = new Resume(FULL_NAME_3);
    protected final Resume[] allResumes = new Resume[]{r1, r2, r3};

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
        Assert.assertArrayEquals(new Resume[0], storage.getAll());
    }

    @Test
    public void update() {
        r1.setFullName("Алиева Олеся");
        storage.update(r1);
        Assert.assertEquals(r1, storage.get(r1.getUuid()));
    }

    @Test
    public void save() {
        Resume r4 = new Resume(FULL_NAME_4);
        storage.save(r4);
        assertGet(r4);
        assertSize(4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(r1.getUuid());
        assertSize(2);
        storage.get(r1.getUuid());
    }

    @Test
    public void getAll() {
        Assert.assertArrayEquals(allResumes, storage.getAll());
    }

    @Test
    public void getAllSorted() {
        for(Resume r : storage.getAll()) {
            System.out.println(r.getFullName());
        }

        System.out.println("");
        for(Resume r : storage.getAllSorted()) {
            System.out.println(r.getFullName());
        }
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void get() {
        assertGet(r1);
        assertGet(r2);
        assertGet(r3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_NOT_EXIST);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_NOT_EXIST);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(storage.get(UUID_NOT_EXIST));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(r1);
    }

    private void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }
}